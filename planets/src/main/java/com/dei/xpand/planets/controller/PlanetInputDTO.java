package com.dei.xpand.planets.controller;

import com.dei.xpand.planets.domain.Planet;
import com.dei.xpand.planets.domain.PlanetStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PlanetInputDTO
{
    @JsonProperty("name")
    @NotNull
    @Size(min = 5, max = 20)
    private String name;

    @JsonProperty("status")
    @NotNull
    private PlanetStatus status;

    public static Planet toDomainEntity(PlanetInputDTO dto)
    {
        Planet planet = new Planet();
        planet.setName(dto.name);
        planet.setStatus(dto.status);
        return planet;
    }
}
