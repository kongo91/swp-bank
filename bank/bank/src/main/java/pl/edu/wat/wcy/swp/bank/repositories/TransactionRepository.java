package pl.edu.wat.wcy.swp.bank.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import pl.edu.wat.wcy.swp.bank.entities.Transaction;
import pl.edu.wat.wcy.swp.bank.entities.TransactionType;

import java.util.List;

/**
 * Created by Konrad on 2015-01-14.
 */
public interface TransactionRepository extends Repository<Transaction, Long> {

    @Query("SELECT t from BankAccount a, Profil p, Customer c, Transaction t WHERE c.customerId = ?1 and c.customerId = p.customer.customerId and a.profil.id = p.id and t.bankAccount.id = a.accountNumber and t.transactionType = ?2 ORDER BY t.transactionDateTime DESC")
    List<Transaction> findLastTransaction(Long cid, TransactionType type);

}
