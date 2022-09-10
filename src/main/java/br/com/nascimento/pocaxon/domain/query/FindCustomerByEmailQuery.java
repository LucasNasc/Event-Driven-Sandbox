/* (C)2022 */
package br.com.nascimento.pocaxon.domain.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindCustomerByEmailQuery {
    private String email;

    public FindCustomerByEmailQuery(String email) {
        this.email = email;
    }
}
