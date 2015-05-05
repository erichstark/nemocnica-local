package sk.stuba.fei.team.local.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by matus_000 on 4.4.2015.
 */
@Entity
public class Facility implements Serializable {

    private Long id;
    private String name;
    private Set<Office> offices;
    private String ulica;
    private String cislo;
    private String mesto;
    private String psc;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facility")
    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    @Column(nullable = false)
    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    @Column(nullable = false)
    public String getCislo() {
        return cislo;
    }

    public void setCislo(String cislo) {
        this.cislo = cislo;
    }

    @Column(nullable = false)
    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    @Column(nullable = false)
    public String getPsc() {
        return psc;
    }

    public void setPsc(String psc) {
        this.psc = psc;
    }
}
