package pl.edu.wat.wcy.swp.bank.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Konrad on 2015-01-03.
 */
@Entity
@XmlRootElement(name="bankaccount")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement
    private Long accountNumber;

    @Column
    @XmlElement
    private String accountName;

    @Column
    @XmlElement
    private Float balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profil_id")
    @XmlTransient
    private Profil profil;

    @OneToMany(mappedBy = "bankAccount")
    @XmlElement
    private Set<Transaction> transactions;

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

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
