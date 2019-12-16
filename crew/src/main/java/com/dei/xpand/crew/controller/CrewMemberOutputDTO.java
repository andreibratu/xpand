package com.dei.xpand.crew.controller;

import com.dei.xpand.crew.domain.CrewMember;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CrewMemberOutputDTO
{
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("is_robot")
    private Boolean isRobot;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static CrewMemberOutputDTO fromCrewMember(CrewMember crewMember)
    {
        CrewMemberOutputDTO dto = new CrewMemberOutputDTO();
        dto.setId(crewMember.getId());
        dto.setName(crewMember.getName());
        dto.setRobot(crewMember.getRobot());
        return dto;
    }
}
