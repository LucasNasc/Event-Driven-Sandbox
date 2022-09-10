/* (C)2022 */
package br.com.nascimento.pocaxon.domain.saga;

import br.com.nascimento.pocaxon.domain.command.StartReedemCommand;
import br.com.nascimento.pocaxon.domain.entity.RedeemEvents;
import br.com.nascimento.pocaxon.domain.query.FindByAggregateIdQuery;
import br.com.nascimento.pocaxon.web.request.StartFlowRequest;
import java.util.UUID;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

@Service
public class StartFlowSaga {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public StartFlowSaga(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    public RedeemEvents start(StartFlowRequest request) throws Exception {
        var aggregateId = UUID.randomUUID().toString();
        commandGateway.sendAndWait(new StartReedemCommand(aggregateId, request.getUserEmail()));
        return queryGateway
                .query(
                        FindByAggregateIdQuery.builder().id(aggregateId).build(),
                        ResponseTypes.multipleInstancesOf(RedeemEvents.class))
                .join()
                .stream()
                .findFirst()
                .orElseThrow(Exception::new);
    }
}
