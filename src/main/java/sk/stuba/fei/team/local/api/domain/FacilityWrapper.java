package sk.stuba.fei.team.local.api.domain;

import sk.stuba.fei.team.local.domain.Facility;

public class FacilityWrapper {
    private Long id;
    private String name;
    private String streetAndNumber;
    private String city;
    private String zip;
    private Boolean enabled;

    public FacilityWrapper() {
    }

    public FacilityWrapper(Facility facility) {
        id = facility.getId();
        name = facility.getName();
        streetAndNumber = facility.getStreetAndNumber();
        city = facility.getCity();
        zip = facility.getZip();
        enabled = facility.getEnabled();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public void setStreetAndNumber(String streetAndNumber) {
        this.streetAndNumber = streetAndNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
