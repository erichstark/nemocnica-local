package sk.stuba.fei.team.local.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Rpi_Office implements Serializable {
    private String id;
    private Set<Office> offices;

    public Rpi_Office() {
    }

    public Rpi_Office(String id, Set<Office> offices) {
        this.id = id;
        this.offices = offices;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "rpiconfiguration_office",
            joinColumns =
            @JoinColumn(name = "rpiconfiguration", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "office", referencedColumnName = "id")
    )
    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }
}
