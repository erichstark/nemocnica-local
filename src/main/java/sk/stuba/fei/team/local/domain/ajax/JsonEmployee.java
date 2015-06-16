package sk.stuba.fei.team.local.domain.ajax;

import sk.stuba.fei.team.local.domain.Employee;

public class JsonEmployee {
    private Employee employee;
    private String specializations;
    private String autority;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSpecializations() {
        return specializations;
    }

    public void setSpecializations(String specializations) {
        this.specializations = specializations;
    }

    public String getAutority() {
        return autority;
    }

    public void setAutority(String autority) {
        this.autority = autority;
    }
}
