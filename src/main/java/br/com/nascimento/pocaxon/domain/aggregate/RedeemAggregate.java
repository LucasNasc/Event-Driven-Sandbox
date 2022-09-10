/* (C)2022 */
package br.com.nascimento.pocaxon.domain.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

import br.com.nascimento.pocaxon.domain.StepNameEnum;
import br.com.nascimento.pocaxon.domain.command.RegisterUserDataCommand;
import br.com.nascimento.pocaxon.domain.command.StartReedemCommand;
import br.com.nascimento.pocaxon.domain.event.RedeemFlowStartedEvent;
import br.com.nascimento.pocaxon.domain.event.RegisterUserEvent;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.MetaData;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class RedeemAggregate {

    @AggregateIdentifier private String id;
    @AggregateMember private String step;
    private String userEmail;

    public RedeemAggregate() {}

    @CommandHandler
    public RedeemAggregate(StartReedemCommand startReedemCommand) {
        log.info("Redeem Code flow started.");
        this.id = startReedemCommand.getId();
        this.userEmail = startReedemCommand.getEmail();
        apply(new RedeemFlowStartedEvent(startReedemCommand.getId(), this.userEmail));
    }

    @CommandHandler
    public void RegisterUserCommand(RegisterUserDataCommand registerUserDataCommand) {
        log.info("register user command started.");
        apply(
                new RegisterUserEvent(
                        registerUserDataCommand.getUserDetails().getName(),
                        this.id,
                        registerUserDataCommand.getUserDetails().getEmail(),
                        registerUserDataCommand.getUserDetails().getLastName()),
                MetaData.from(Map.of("STEP", this.step)));
    }

    @EventSourcingHandler
    public RedeemFlowStartedEvent on(RedeemFlowStartedEvent redeemFlowStartedEvent) {
        log.info("Redeem flow started.");
        this.id = redeemFlowStartedEvent.getId();
        this.userEmail = redeemFlowStartedEvent.getUserEmail();
        this.step = StepNameEnum.START_REDEEM.name();
        return redeemFlowStartedEvent;
    }

    @EventSourcingHandler
    public void RegisterUserEvent(RegisterUserEvent registerUserEvent) {
        log.info("Registering user");
        this.step = StepNameEnum.REGISTER_USER_DATA.name();
    }
}
