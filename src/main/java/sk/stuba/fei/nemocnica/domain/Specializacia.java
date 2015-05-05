package sk.stuba.fei.nemocnica.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Specializacia implements Serializable {

    private Long id;
    private String nazov;
    private Set<Ambulancia> ambulancie;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(nullable = false)
    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    @ManyToMany(mappedBy = "specializacie")
    public Set<Ambulancia> getAmbulancie() {
        return ambulancie;
    }

    public void setAmbulancie(Set<Ambulancia> ambulancie) {
        this.ambulancie = ambulancie;
    }
}
