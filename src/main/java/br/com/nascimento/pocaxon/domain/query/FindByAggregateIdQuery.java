/* (C)2022 */
package br.com.nascimento.pocaxon.domain.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FindByAggregateIdQuery {
    private String id;

    public FindByAggregateIdQuery(String id) {
        this.id = id;
    }
}
