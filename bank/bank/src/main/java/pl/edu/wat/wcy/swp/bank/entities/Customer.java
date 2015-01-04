package pl.edu.wat.wcy.swp.bank.entities;


import javax.persistence.*;

/**
 * Created by Konrad on 2015-01-03.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @Column
    private Integer PIN;


    @OneToOne (mappedBy = "customer")
    private Profil profil;


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getPIN() {
        return PIN;
    }

    public void setPIN(Integer PIN) {
        this.PIN = PIN;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }
}
