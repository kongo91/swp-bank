package pl.edu.wat.wcy.swp.bank.dtos;

import javax.persistence.Column;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Konrad on 2015-01-14.
 */
@XmlRootElement(name="transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionDTO {

    @XmlElement
    private Long id;

    @XmlElement
    private String date;

    @XmlElement
    private String time;

    @XmlElement
    private Float amount;

    @XmlElement
    private String name;

    @XmlElement
    private String surname;

    @XmlElement
    private String adress;

    @XmlElement
    private String title;

    @XmlElement
    private String transactionType;

    @XmlElement
    private Boolean isTransaction;

    public Boolean getIsTransaction() {
        return isTransaction;
    }

    public void setIsTransaction(Boolean isTransaction) {
        this.isTransaction = isTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
}
