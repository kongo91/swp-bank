package pl.edu.wat.wcy.swp.bank.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Created by Konrad on 2015-01-03.
 */
@Entity
@XmlRootElement(name="transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement
    private Long id;


    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    @Column
    @Type(type = "timestamp")
    @XmlElement
    private Date transactionDateTime;

    @Column
    @XmlElement
    private Float amount;

    @Column
    @XmlElement
    private String name;

    @Column
    @XmlElement
    private String surname;

    @Column
    @Lob
    @XmlElement
    private String adress;

    @Column
    @XmlElement
    private String title;

    @Column
    @XmlElement
    private Float balanceBefore;

    @Column
    @Enumerated(EnumType.STRING)
    @XmlElement
    private TransactionType transactionType;

    @Column
    private String accountNumber;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Date getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Date transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getBalanceBefore() {
        return balanceBefore;
    }

    public void setBalanceBefore(Float balanceBefore) {
        this.balanceBefore = balanceBefore;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
