package com.dei.xpand.planets.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
public enum PlanetStatus
{
    OK (0), NOT_OK (1), TODO (2), EN_ROUTE (3);

    private Integer value;

    PlanetStatus(Integer value) { this.value = value; }
}
