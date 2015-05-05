package sk.stuba.fei.team.local.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by matus_000 on 15.4.2015.
 */
@Entity
public class Patient implements Serializable {
    private Long id;
    private String name;
    private String surname;
    private String telefon;
    private String email;
    private String titul;
    private Insurance insurance;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitul() {
        return titul;
    }

    public void setTitul(String titul) {
        this.titul = titul;
    }

    @ManyToOne
    @JoinColumn(name = "insurance", nullable = false)
    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }
}
