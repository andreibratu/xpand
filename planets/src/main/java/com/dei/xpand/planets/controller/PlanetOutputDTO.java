package com.dei.xpand.planets.controller;

import com.dei.xpand.planets.domain.Planet;
import com.dei.xpand.planets.domain.PlanetStatus;
import com.fasterxml.jackson.annotation.JsonProperty;


public class PlanetOutputDTO
{
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image_path")
    private String imagePath;

    @JsonProperty("status")
    private PlanetStatus status;

    public static PlanetOutputDTO fromDomainEntity(Planet planet)
    {
        PlanetOutputDTO dto = new PlanetOutputDTO();
        dto.id = planet.getId();
        dto.name = planet.getName();
        dto.imagePath = planet.getImagePath();
        dto.status = planet.getStatus();
        return dto;
    }
}
