package sk.stuba.fei.nemocnica.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by matus_000 on 4.4.2015.
 */
@Entity
public class Ambulancia implements Serializable {

    private int id;
    private String name;
    private Zariadenie zariadenie;
    private Set<Zamestnanec> zamestnanci;
    private Set<Poistovna> poistovne;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(mappedBy = "ambulancie")
    public Set<Zamestnanec> getZamestnanci() {
        return zamestnanci;
    }

    public void setZamestnanci(Set<Zamestnanec> zamestnanci) {
        this.zamestnanci = zamestnanci;
    }

    @ManyToOne
    @JoinColumn(name = "zariadenie", nullable = false)
    public Zariadenie getZariadenie() {
        return zariadenie;
    }

    public void setZariadenie(Zariadenie zariadenie) {
        this.zariadenie = zariadenie;
    }


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "ambulancia_poistovna",
            joinColumns =
            @JoinColumn(name = "ambulancia", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "poistovna", referencedColumnName = "id")
    )
    public Set<Poistovna> getPoistovne() {
        return poistovne;
    }

    public void setPoistovne(Set<Poistovna> poistovne) {
        this.poistovne = poistovne;
    }
}
