package pl.edu.wat.wcy.swp.bank.entities;


import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by Konrad on 2015-01-03.
 */
@Entity
@XmlRootElement(name="customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class Customer {

    @Id
    @XmlElement
    private Long customerId;

    @Column
    @XmlElement
    private String PIN;


    @OneToOne (mappedBy = "customer",fetch = FetchType.LAZY)
    @XmlElement
    @Cascade({CascadeType.ALL, CascadeType.DELETE, CascadeType.SAVE_UPDATE })
    private Profil profil;

    @Column
    @Type(type = "timestamp")
    @XmlElement
    private Date lastLoginTime;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
