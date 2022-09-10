/* (C)2022 */
package br.com.nascimento.pocaxon.domain.repository;

import br.com.nascimento.pocaxon.domain.entity.RedeemEvents;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedeemEventsRepository extends JpaRepository<RedeemEvents, UUID> {

    List<RedeemEvents> findByAggregateId(String aggregateId);
}
