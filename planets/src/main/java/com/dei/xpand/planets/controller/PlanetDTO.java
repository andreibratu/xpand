package com.dei.xpand.planets.controller;

import com.dei.xpand.planets.domain.Planet;
import com.dei.xpand.planets.domain.PlanetStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanetDTO
{
    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image_path")
    private String imagePath;

    @JsonProperty("status")
    private PlanetStatus status;

    public PlanetDTO(Planet planet)
    {
        id = planet.getId();
        name = planet.getName();
        imagePath = planet.getImagePath();
        status = planet.getStatus();
    }
}
