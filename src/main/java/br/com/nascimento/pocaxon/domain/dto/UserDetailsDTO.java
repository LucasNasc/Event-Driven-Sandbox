/* (C)2022 */
package br.com.nascimento.pocaxon.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDTO {

    private String name;
    private String lastName;
    private String email;
}
