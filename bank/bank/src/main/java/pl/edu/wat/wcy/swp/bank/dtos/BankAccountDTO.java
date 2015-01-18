package pl.edu.wat.wcy.swp.bank.dtos;

import javax.xml.bind.annotation.*;
import java.util.Set;

/**
 * Created by Konrad on 2015-01-18.
 */
@XmlRootElement(name="transaction")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountDTO {

    @XmlElement
    private String accountNumber;

    @XmlElement
    private String accountType;

    @XmlElement
    private Float balance;

    @XmlElement
    private String currency;

    @XmlElement
    private Boolean isCard;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Boolean getIsCard() {
        return isCard;
    }

    public void setIsCard(Boolean isCard) {
        this.isCard = isCard;
    }
}
