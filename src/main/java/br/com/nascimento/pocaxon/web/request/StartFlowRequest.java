/* (C)2022 */
package br.com.nascimento.pocaxon.web.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartFlowRequest {

    private String userEmail;
}
