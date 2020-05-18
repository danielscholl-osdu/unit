package org.opengroup.osdu.unitservice.v2.model.extended;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitSystemInfo {
    private String name;
    private String description;
    private String ancestry;
    private String persistableReference;

    public UnitSystemInfo(String name, String description, String ancestry, String persistableReference) {
        this.name = name;
        this.description = description;
        this.ancestry = ancestry;
        this.persistableReference = persistableReference;
    }

    @JsonProperty("name")
    public String getName() {
        return this.name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return this.description;
    }
    @JsonProperty("ancestry")
    public String getAncestry() {
        return this.ancestry;
    }

    @JsonProperty("persistableReference")
    public String getPersistableReference() {
        return this.persistableReference;
    }
}
