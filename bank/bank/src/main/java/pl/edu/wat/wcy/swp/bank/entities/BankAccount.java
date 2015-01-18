package pl.edu.wat.wcy.swp.bank.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.xml.bind.annotation.*;
import java.util.Set;

/**
 * Created by Konrad on 2015-01-03.
 */
@Entity
@XmlRootElement(name="bankaccount")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccount {

    @Id
    @XmlElement
    private String accountNumber;

    @Column
    @XmlElement
    @Enumerated(EnumType.STRING)
    private BankAccountType accountType;

    @Column
    @XmlElement
    private Float balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profil_id")
    @XmlTransient
    private Profil profil;

    @OneToMany(mappedBy = "bankAccount")
    @XmlElement
    @Cascade({CascadeType.ALL, CascadeType.DELETE, CascadeType.SAVE_UPDATE })
    private Set<Transaction> transactions;

    @Column
    @Enumerated(EnumType.STRING)
    private CurrencyType currency;

    @Column
    private Boolean isCard;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BankAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(BankAccountType accountType) {
        this.accountType = accountType;
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

    public CurrencyType getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyType currency) {
        this.currency = currency;
    }

    public Boolean getIsCard() {
        return isCard;
    }

    public void setIsCard(Boolean isCard) {
        this.isCard = isCard;
    }
}
