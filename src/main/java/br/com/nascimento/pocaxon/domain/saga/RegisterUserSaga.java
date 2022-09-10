/* (C)2022 */
package br.com.nascimento.pocaxon.domain.saga;

import br.com.nascimento.pocaxon.domain.command.RegisterUserDataCommand;
import br.com.nascimento.pocaxon.domain.entity.Customer;
import br.com.nascimento.pocaxon.domain.entity.RedeemEvents;
import br.com.nascimento.pocaxon.domain.query.FindByAggregateIdQuery;
import br.com.nascimento.pocaxon.domain.query.FindCustomerByEmailQuery;
import br.com.nascimento.pocaxon.web.request.RegisteUserStepRequest;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterUserSaga {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public RegisterUserSaga(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public Customer start(RegisteUserStepRequest request) throws Exception {
        var events =
                queryGateway
                        .query(
                                FindByAggregateIdQuery.builder()
                                        .id(request.getAggregateId())
                                        .build(),
                                ResponseTypes.multipleInstancesOf(RedeemEvents.class))
                        .join();

        if (events.isEmpty()) {
            log.info("aggregate Id doesnt Exist");
            throw new Exception();
        }

        commandGateway.sendAndWait(
                new RegisterUserDataCommand(request.getAggregateId(), request.getDto()));

        return queryGateway
                .query(
                        FindCustomerByEmailQuery.builder()
                                .email(request.getDto().getEmail())
                                .build(),
                        Customer.class)
                .join();
    }
}
