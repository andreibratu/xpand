package com.dei.xpand.planets.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND, reason="Could not find planet with given id")
public class PlanetNotFoundException extends RuntimeException {}