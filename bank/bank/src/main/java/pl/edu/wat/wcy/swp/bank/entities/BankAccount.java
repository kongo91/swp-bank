package pl.edu.wat.wcy.swp.bank.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Konrad on 2015-01-03.
 */
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountNumber;

    @Column
    private String accountName;

    @Column
    private Float balance;

    @ManyToOne
    @JoinColumn(name="profil_id")
    private Profil profil;

    @OneToMany(mappedBy = "bankAccount")
    private List<Transaction> transactions;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
