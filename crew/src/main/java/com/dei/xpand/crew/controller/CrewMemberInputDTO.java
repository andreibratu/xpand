package com.dei.xpand.crew.controller;

import com.dei.xpand.crew.domain.CrewMember;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CrewMemberInputDTO
{
    @JsonProperty("id")
    @Nullable
    private Integer id;

    @JsonProperty("name")
    @Size(min=3, max=(30))
    private String name;

    @JsonProperty("is_robot")
    @NotNull
    private Boolean isRobot;

    @JsonProperty("crew_id")
    @NotNull
    private Integer crewId;

    public CrewMemberInputDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRobot() {
        return isRobot;
    }

    public void setRobot(Boolean robot) {
        isRobot = robot;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getCrewId() {
        return crewId;
    }

    public void setCrewId(Integer crewId) {
        this.crewId = crewId;
    }

    public static CrewMember toDomainEntity(CrewMemberInputDTO dto)
    {
        CrewMember crewMember = new CrewMember();
        crewMember.setName(dto.getName());
        crewMember.setRobot(dto.getRobot());
        crewMember.setCrewId(dto.getCrewId());
        return crewMember;
    }
}
