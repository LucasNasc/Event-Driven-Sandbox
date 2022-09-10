/* (C)2022 */
package br.com.nascimento.pocaxon.domain.event;

import lombok.Data;

@Data
public class RedeemFlowStartedEvent {

    private final String id;
    private final String userEmail;

    public RedeemFlowStartedEvent(String id, String userEmail) {
        this.id = id;
        this.userEmail = userEmail;
    }
}
