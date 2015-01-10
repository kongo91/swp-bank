package pl.edu.wat.wcy.swp.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.wat.wcy.swp.bank.entities.Customer;

/**
 * Created by Konrad on 2015-01-03.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where c.customerId = ?1 and c.PIN = ?2")
    Customer findByIdAndPin(Long customerId, Integer Pin);

}
