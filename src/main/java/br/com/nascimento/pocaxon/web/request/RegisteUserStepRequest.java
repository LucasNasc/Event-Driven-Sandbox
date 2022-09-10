/* (C)2022 */
package br.com.nascimento.pocaxon.web.request;

import br.com.nascimento.pocaxon.domain.dto.UserDetailsDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisteUserStepRequest {

    private String aggregateId;
    private UserDetailsDTO dto;
}
