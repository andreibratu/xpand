package com.dei.xpand.crew.domain;

import com.dei.xpand.crew.controller.CrewMemberInputDTO;

import javax.persistence.*;

@Entity
@Table(name = "crew")
public class CrewMember
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean isRobot;

    @Column(nullable = false)
    private Integer crewId;

    public CrewMember() { }

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

    public Integer getCrewId() { return crewId; }

    public void setCrewId(Integer crewId) { this.crewId = crewId; }
}
