/* (C)2022 */
package br.com.nascimento.pocaxon.domain.query;

import br.com.nascimento.pocaxon.domain.StepNameEnum;
import br.com.nascimento.pocaxon.domain.entity.Customer;
import br.com.nascimento.pocaxon.domain.entity.RedeemEvents;
import br.com.nascimento.pocaxon.domain.event.RedeemFlowStartedEvent;
import br.com.nascimento.pocaxon.domain.event.RegisterUserEvent;
import br.com.nascimento.pocaxon.domain.repository.CustomerRepository;
import br.com.nascimento.pocaxon.domain.repository.RedeemEventsRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReedemEventsQueryHandler {

    private final RedeemEventsRepository repository;
    private final CustomerRepository customerRepository;

    public ReedemEventsQueryHandler(
            RedeemEventsRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @EventHandler
    @Transactional
    public RedeemFlowStartedEvent createEvent(RedeemFlowStartedEvent redeemFlowStartedEvent) {
        repository.save(
                RedeemEvents.builder()
                        .aggregateId(redeemFlowStartedEvent.getId())
                        .userEmail(redeemFlowStartedEvent.getUserEmail())
                        .step(StepNameEnum.START_REDEEM.name())
                        .build());
        log.info("event saved");
        return redeemFlowStartedEvent;
    }

    @EventHandler
    @Transactional
    public Customer registerUserEvent(RegisterUserEvent registerUserEvent) {
        RedeemEvents event =
                repository.findByAggregateId(registerUserEvent.getAgreggateId()).get(0);

        repository.save(
                RedeemEvents.builder()
                        .aggregateId(event.getAggregateId())
                        .userEmail(event.getUserEmail())
                        .step(StepNameEnum.REGISTER_USER_DATA.name())
                        .build());

        return customerRepository.save(
                Customer.builder()
                        .email(registerUserEvent.getUserEmail())
                        .name(registerUserEvent.getName())
                        .lastName(registerUserEvent.getLastName())
                        .build());
    }

    @QueryHandler
    public List<RedeemEvents> findByAggregateIdQuery(
            FindByAggregateIdQuery findByAggregateIdQuery) {
        return repository.findByAggregateId(findByAggregateIdQuery.getId());
    }

    @QueryHandler
    public Customer findCustomerByEmailQuery(FindCustomerByEmailQuery findCustomerByEmailQuery) {
        return customerRepository.findByEmail(findCustomerByEmailQuery.getEmail());
    }
}
