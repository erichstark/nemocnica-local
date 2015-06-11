package sk.stuba.fei.team.local.api;

import java.util.Set;

public class RpiConfigMessage extends RestMessage {
    private final Set<Long> offices;

    public RpiConfigMessage(Set<Long> offices) {
        this.offices = offices;
    }

    public Set<Long> getOffices() {
        return offices;
    }
}
