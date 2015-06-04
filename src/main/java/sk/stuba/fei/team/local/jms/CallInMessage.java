package sk.stuba.fei.team.local.jms;

import java.io.Serializable;

public class CallInMessage implements Serializable {
    private Long office;
    private String patient;

    public CallInMessage() {
    }

    public CallInMessage(Long office, String patient) {
        this.office = office;
        this.patient = patient;
    }

    public Long getOffice() {
        return office;
    }

    public void setOffice(Long office) {
        this.office = office;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }
}
