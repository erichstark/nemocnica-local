package sk.stuba.fei.team.local.api.domain;

import sk.stuba.fei.team.local.domain.Patient;
import sk.stuba.fei.team.local.repository.PatientRepository;
import sk.stuba.fei.team.local.service.InsuranceService;

public class PatientWrapper {
    private String password;
    private String username;
    private boolean enabled;
    private String firstName;
    private String surname;
    private String phone;
    private String email;
    private String prefix_title;
    private String suffix_title;
    private Long insurance;

    public PatientWrapper() {
    }

    public PatientWrapper(Patient patient) {
        password = patient.getPassword();
        username = patient.getUsername();
        enabled = patient.isEnabled();
        firstName = patient.getFirstName();
        surname = patient.getSurname();
        phone = patient.getPhone();
        email = patient.getEmail();
        prefix_title = patient.getPrefix_title();
        suffix_title = patient.getSuffix_title();
        insurance = patient.getInsurance().getId();
    }

    public Patient build(PatientRepository patientRepository, InsuranceService insuranceService) {
        Patient patient = new Patient();
        patient.setPassword(password);
        patient.setUsername(username);
        patient.setEnabled(enabled);
        patient.setFirstName(firstName);
        patient.setSurname(surname);
        patient.setPhone(phone);
        patient.setEmail(email);
        patient.setPrefix_title(prefix_title);
        patient.setSuffix_title(suffix_title);
        patient.setInsurance(insuranceService.findOne(insurance));
        Patient oldPatient = patientRepository.findOne(username);
        if (oldPatient != null) {
            patient.getAppointments().addAll(oldPatient.getAppointments());
        }
        return patient;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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

    public Long getInsurance() {
        return insurance;
    }

    public void setInsurance(Long insurance) {
        this.insurance = insurance;
    }
}
