package sk.stuba.fei.team.local.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Advertisement implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String filename;
    private Set<Specialization> specializations;
    private Set<Facility> facilities;

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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(nullable = false)
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "advertisement_specialization",
            joinColumns =
            @JoinColumn(name = "advertisement", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "specialization", referencedColumnName = "id")
    )
    public Set<Specialization> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<Specialization> specializations) {
        this.specializations = specializations;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "advertisement_facility",
            joinColumns =
            @JoinColumn(name = "advertisement", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "facility", referencedColumnName = "id")
    )
    public Set<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }
}