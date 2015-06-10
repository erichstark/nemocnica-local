package sk.stuba.fei.team.local.api.domain;

import sk.stuba.fei.team.local.domain.Specialization;

public class SpecializationWrapper {
    private Long id;
    private String name;
    private Boolean enabled;

    public SpecializationWrapper() {
    }

    public SpecializationWrapper(Specialization specialization) {
        id = specialization.getId();
        name = specialization.getName();
        enabled = specialization.getEnabled();
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
