/* (C)2022 */
package br.com.nascimento.pocaxon.web.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StartStepResponse {

    private String aggregateId;
}
