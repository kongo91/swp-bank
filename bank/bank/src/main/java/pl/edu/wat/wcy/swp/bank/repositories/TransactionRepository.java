package pl.edu.wat.wcy.swp.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.wat.wcy.swp.bank.entities.Transaction;
import pl.edu.wat.wcy.swp.bank.entities.TransactionType;

import java.util.List;

/**
 * Created by Konrad on 2015-01-14.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t from BankAccount a, Profil p, Customer c, Transaction t WHERE c.customerId = ?1 and c.customerId = p.customer.customerId and a.profil.id = p.id and t.bankAccount.id = a.accountNumber and t.transactionType = ?2 ORDER BY t.transactionDateTime DESC")
    List<Transaction> findLastTransactionsByType(Long cid, TransactionType type);

    @Query("SELECT t from BankAccount a, Profil p, Customer c, Transaction t WHERE c.customerId = ?1 and c.customerId = p.customer.customerId and a.profil.id = p.id and t.bankAccount.id = a.accountNumber ORDER BY t.transactionDateTime DESC")
    List<Transaction> findaAllByCustomerId(Long cid);

}
