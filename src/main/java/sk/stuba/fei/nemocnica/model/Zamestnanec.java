package sk.stuba.fei.nemocnica.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by matus_000 on 4.4.2015.
 */
@Entity
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Zamestnanec.findAll", query = "SELECT z FROM Zamestnanec z"),
        @NamedQuery(name = "Zamestnanec.findById", query = "SELECT z FROM Zamestnanec z WHERE z.id = :id"),
        @NamedQuery(name = "Zamestnanec.findByRole", query = "SELECT z FROM Zamestnanec z WHERE z.role LIKE :role"),
        @NamedQuery(name = "Zamestnanec.findByUsername", query = "SELECT z FROM Zamestnanec z WHERE z.username = :username"),
        @NamedQuery(name = "Zamestnanec.findDoctors", query = "SELECT z FROM Zamestnanec z  WHERE (concat(z.name,' ',z.surname) like :fullName " +
                "    or concat(z.name,' ',z.surname) like :fullName) and z.role='doctor'" )})
public class Zamestnanec implements Serializable {

    private int id;
    private String username;
    private String password;
    private String role;
    private String name;
    private String surname;

    private Set<Ambulancia> ambulancie;
    private List<String> specializations;

    public Zamestnanec() {
        id = 0;
        username = "";
        password = "";
        role = "";
        name= "";
        surname= "";
        ambulancie = new HashSet<>();
        specializations = new ArrayList<>();
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "zamestnanec_ambulancia",
            joinColumns =
            @JoinColumn(name = "zamestnanec", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "ambulancia", referencedColumnName = "id")
    )
    public Set<Ambulancia> getAmbulancie() {
        return ambulancie;
    }

    public void setAmbulancie(Set<Ambulancia> ambulancie) {
        this.ambulancie = ambulancie;
    }

    @ElementCollection(fetch=FetchType.EAGER)
    public List<String> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<String> specializations) {
        this.specializations = specializations;
    }
}
