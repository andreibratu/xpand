package com.dei.xpand.planets.response;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason="Bad planet status")
public class InvalidPlanetStatusException extends RuntimeException { }
