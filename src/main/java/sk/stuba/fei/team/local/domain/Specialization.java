package sk.stuba.fei.team.local.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
public class Specialization implements Serializable {

    private static final long serialVersionUID = 7118518081051227683L;
    private Long id;
    private String name;
    private Set<Office> offices;
    private Set<Employee> employees;
    private Boolean enabled;

    public Specialization() {
        name = "";
        offices = new HashSet<>();
        employees = new HashSet<>();
        enabled = true;
    }

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

    @ManyToMany(mappedBy = "specializations")
    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices.clear();
        this.offices.addAll(offices);
    }

    @ManyToMany(mappedBy = "specializations")
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees.clear();
        this.employees.addAll(employees);
    }

    @Column(nullable = false)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
