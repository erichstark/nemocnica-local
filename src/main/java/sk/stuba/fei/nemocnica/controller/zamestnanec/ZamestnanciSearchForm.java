package sk.stuba.fei.nemocnica.controller.zamestnanec;

/**
 * Created by jakubrehak on 20/04/15.
 */
public class ZamestnanciSearchForm extends sk.stuba.fei.nemocnica.search.GenericSearchForm {

    private String name;
    private String surname;
    private String specialization;
    private String town;

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
