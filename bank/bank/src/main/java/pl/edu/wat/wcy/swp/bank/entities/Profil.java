package pl.edu.wat.wcy.swp.bank.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Set;

/**
 * Created by Konrad on 2015-01-03.
 */
@Entity
@XmlRootElement(name="profil")
@XmlAccessorType(XmlAccessType.FIELD)
public class Profil {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlElement
    private Long id;

    @Column
    @XmlElement
    private String name;

    @Column
    @XmlElement
    private String surname;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    @XmlElement
    private String email;

    @Column
    @XmlElement
    private String phoneNumber;

    @Column
    @Lob
    @XmlElement
    private String adress;

    @OneToMany(mappedBy = "profil")
    @XmlElement
    @Cascade({CascadeType.ALL, CascadeType.DELETE, CascadeType.SAVE_UPDATE })
    private Set<BankAccount> bankAccounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
