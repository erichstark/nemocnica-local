package sk.stuba.fei.team.local.api;

import sk.stuba.fei.team.local.domain.Office;

public class RpiOffice {
    private Long id;
    private String name;

    public RpiOffice(Office office) {
        id = office.getId();
        name = office.getName();
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
}
