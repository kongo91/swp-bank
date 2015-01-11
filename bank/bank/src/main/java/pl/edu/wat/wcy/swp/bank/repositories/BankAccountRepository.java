package pl.edu.wat.wcy.swp.bank.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.edu.wat.wcy.swp.bank.entities.BankAccount;

/**
 * Created by Konrad on 2015-01-11.
 */
public interface BankAccountRepository extends Repository<BankAccount, Long> {


    @Query("SELECT SUM(a.balance) from BankAccount a, Profil p, Customer c WHERE c.customerId = ?1 and c.customerId = p.customer.customerId and a.profil.id = p.id")
    Float getBalance(long customerId);

}
