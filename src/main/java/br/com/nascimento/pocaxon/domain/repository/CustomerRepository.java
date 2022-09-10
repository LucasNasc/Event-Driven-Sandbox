/* (C)2022 */
package br.com.nascimento.pocaxon.domain.repository;

import br.com.nascimento.pocaxon.domain.entity.Customer;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Customer findByEmail(String email);
}
