/* (C)2022 */
package br.com.nascimento.pocaxon.domain.event;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class RegisterUserEvent {

    private final String name;
    private final String agreggateId;
    private final String userEmail;
    private final String lastName;

    public RegisterUserEvent(String name, String agreggateId, String userEmail, String lastName) {
        this.name = name;
        this.agreggateId = agreggateId;
        this.userEmail = userEmail;
        this.lastName = lastName;
    }
}
