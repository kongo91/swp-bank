package pl.edu.wat.wcy.swp.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.wcy.swp.bank.entities.Customer;

/**
 * Created by Konrad on 2015-01-03.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
