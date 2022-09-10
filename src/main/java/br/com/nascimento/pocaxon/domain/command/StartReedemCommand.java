/* (C)2022 */
package br.com.nascimento.pocaxon.domain.command;

import lombok.Data;

@Data
public class StartReedemCommand {
    private final String email;
    private final String id;

    public StartReedemCommand(String id, String email) {
        this.id = id;
        this.email = email;
    }
}
