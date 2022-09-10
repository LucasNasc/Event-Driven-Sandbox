/* (C)2022 */
package br.com.nascimento.pocaxon.domain.entity;

import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@Entity
@Table(name = "REEDEM_EVENTS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedeemEvents {

    @Id
    @Column(columnDefinition = "uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String aggregateId;

    private String userEmail;

    private String step;
}
