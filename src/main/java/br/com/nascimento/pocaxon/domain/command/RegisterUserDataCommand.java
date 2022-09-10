/* (C)2022 */
package br.com.nascimento.pocaxon.domain.command;

import br.com.nascimento.pocaxon.domain.dto.UserDetailsDTO;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class RegisterUserDataCommand {

    private final UserDetailsDTO userDetails;
    @TargetAggregateIdentifier private final String id;

    public RegisterUserDataCommand(String id, UserDetailsDTO userDetails) {
        this.userDetails = userDetails;
        this.id = id;
    }
}
