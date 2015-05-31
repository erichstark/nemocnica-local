package sk.stuba.fei.team.local.domain;

import java.io.Serializable;
import java.util.List;

public class CallInMessage implements Serializable {
    private Long office;
    private String patient;
    private List<String> nextPatients;

    public CallInMessage(Long office, String patient, List<String> nextPatients) {
        this.office = office;
        this.patient = patient;
        this.nextPatients = nextPatients;
    }

    public Long getOffice() {
        return office;
    }

    public String getPatient() {
        return patient;
    }

    public List<String> getNextPatients() {
        return nextPatients;
    }
}
