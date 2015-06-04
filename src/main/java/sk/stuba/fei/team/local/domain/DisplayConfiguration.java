package sk.stuba.fei.team.local.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DisplayConfiguration implements Serializable {
    private String id;
    private Set<Office> offices;

    public DisplayConfiguration() {
        id = "";
        offices = new HashSet<>();
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
            name = "displayConfiguration_office",
            joinColumns =
            @JoinColumn(name = "displayConfiguration", referencedColumnName = "id"),
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
