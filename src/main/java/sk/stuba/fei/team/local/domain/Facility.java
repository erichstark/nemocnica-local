package sk.stuba.fei.team.local.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@XmlRootElement
public class Facility implements Serializable {

    private Long id;
    private String name;
    private Set<Office> offices;
    private String streetAndNumber;
    private String city;
    private String zip;
    private String clientID;
    private String clientSecret;
    private String username;
    private String password;
    private Boolean enabled;
    private Date employeesUpdated;
    private Date patientsUpdated;
    private Date appointmentsUpdated;
    private Date specializationsUpdated;
    private Date insurancesUpdated;

    public Facility() {
        enabled = true;
        employeesUpdated = new Date(0);
        patientsUpdated = new Date(0);
        appointmentsUpdated = new Date(0);
        specializationsUpdated = new Date(0);
        insurancesUpdated = new Date(0);
    }

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "facility")
    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    @Column(nullable = false)
    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    @Column(nullable = false)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(nullable = false)
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Column(nullable = false)
    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    @Column(nullable = false)
    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    @Column(nullable = false)
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
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getEmployeesUpdated() {
        return employeesUpdated;
    }

    public void setEmployeesUpdated(Date employeesUpdated) {
        this.employeesUpdated = employeesUpdated;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getPatientsUpdated() {
        return patientsUpdated;
    }

    public void setPatientsUpdated(Date patientsUpdated) {
        this.patientsUpdated = patientsUpdated;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getAppointmentsUpdated() {
        return appointmentsUpdated;
    }

    public void setAppointmentsUpdated(Date appointmentsUpdated) {
        this.appointmentsUpdated = appointmentsUpdated;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getSpecializationsUpdated() {
        return specializationsUpdated;
    }

    public void setSpecializationsUpdated(Date specializationsUpdated) {
        this.specializationsUpdated = specializationsUpdated;
    }

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getInsurancesUpdated() {
        return insurancesUpdated;
    }

    public void setInsurancesUpdated(Date insurancesUpdated) {
        this.insurancesUpdated = insurancesUpdated;
    }
}