package sk.stuba.fei.nemocnica.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

/**
 * Created by matus_000 on 15.4.2015.
 */
@Entity
@XmlRootElement
public class Poistovna {
    private int id;
    private String nazov;
    private Set<Ambulancia> ambulancie;
    private Set<Pacient> pacienti;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(unique = true, nullable = false)
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @ManyToMany(mappedBy = "poistovne")
    public Set<Ambulancia> getAmbulancie() {
        return ambulancie;
    }

    public void setAmbulancie(Set<Ambulancia> ambulancie) {
        this.ambulancie = ambulancie;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "poistovna")
    public Set<Pacient> getPacienti() {
        return pacienti;
    }

    public void setPacienti(Set<Pacient> pacienti) {
        this.pacienti = pacienti;
    }
}
