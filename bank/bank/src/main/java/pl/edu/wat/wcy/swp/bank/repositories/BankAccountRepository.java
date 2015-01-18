package pl.edu.wat.wcy.swp.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.edu.wat.wcy.swp.bank.entities.BankAccount;

import java.util.List;

/**
 * Created by Konrad on 2015-01-11.
 */
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {


    @Query("SELECT SUM(a.balance) from BankAccount a, Profil p, Customer c WHERE c.customerId = ?1 and c.customerId = p.customer.customerId and a.profil.id = p.id")
    Float getBalance(long customerId);


    @Query("SELECT a FROM BankAccount a, Profil p, Customer c WHERE c.customerId = ?1 and c.customerId = p.customer.customerId and a.profil.id = p.id")
    List<BankAccount> getBankAccountByCustomerId(long customerId);

}
