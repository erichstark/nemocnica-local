package sk.stuba.fei.team.local.rest;

import java.util.Set;

public class RpiConfigMessage extends RestMessage {
    private Set<String> advertisements;

    public RpiConfigMessage(Set<String> advertisements) {
        this.advertisements = advertisements;
    }

    public Set<String> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<String> advertisements) {
        this.advertisements = advertisements;
    }
}
