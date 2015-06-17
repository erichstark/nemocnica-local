package sk.stuba.fei.team.local.api;

import java.util.Set;

public class RpiConfigMessage extends RestMessage {
    private Set<RpiOffice> offices;

    public RpiConfigMessage(Set<RpiOffice> offices) {
        this.offices = offices;
    }

    public Set<RpiOffice> getOffices() {
        return offices;
    }

    public void setOffices(Set<RpiOffice> offices) {
        this.offices = offices;
    }
}
