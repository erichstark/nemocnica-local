package sk.stuba.fei.nemocnica.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by matus_000 on 15.4.2015.
 */
@Entity
@XmlRootElement
public class Pacient {
    private int id;
    private String meno;
    private String priezvisko;
    private String telefon;
    private String email;
    private String titul;
    private Poistovna poistovna;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    @Column(nullable = false)
    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
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
    @JoinColumn(name = "poistovna", nullable = false)
    public Poistovna getPoistovna() {
        return poistovna;
    }

    public void setPoistovna(Poistovna poistovna) {
        this.poistovna = poistovna;
    }
}
