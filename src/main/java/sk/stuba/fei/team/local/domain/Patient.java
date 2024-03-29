package sk.stuba.fei.team.local.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@XmlRootElement
public class Patient implements Serializable {

    private static final long serialVersionUID = 1124023639612447072L;
    private String password;
    private String username;
    private boolean enabled;
    private String firstName;
    private String surname;
    private String phone;
    private String email;
    private String prefix_title;
    private String suffix_title;
    private Insurance insurance;
    private Set<Appointment> appointments;

    public Patient() {
        password = "";
        username = "";
        enabled = true;
        firstName = "";
        surname = "";
        prefix_title = "";
        suffix_title = "";
        phone = "";
        email = "";
        appointments = new HashSet<>();
    }

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(columnDefinition = "BOOLEAN NOT NULL DEFAULT TRUE")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrefix_title() {
        return prefix_title;
    }

    public void setPrefix_title(String prefix_title) {
        this.prefix_title = prefix_title;
    }

    public String getSuffix_title() {
        return suffix_title;
    }

    public void setSuffix_title(String suffix_title) {
        this.suffix_title = suffix_title;
    }

    @ManyToOne
    @JoinColumn(name = "insurance")
    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "patient")
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments.clear();
        this.appointments.addAll(appointments);
    }
}
