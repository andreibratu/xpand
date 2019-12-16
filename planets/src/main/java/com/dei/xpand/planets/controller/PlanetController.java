package com.dei.xpand.planets.controller;

import com.dei.xpand.planets.domain.Planet;
import com.dei.xpand.planets.response.PlanetNotFoundException;
import com.dei.xpand.planets.service.PlanetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PlanetController
{
    public final PlanetService planetService;

    public PlanetController(PlanetService planetService) { this.planetService = planetService; }

    @RequestMapping(value = "/planet", method = RequestMethod.GET)
    public ResponseEntity<?> findAll()
    {
        return ResponseEntity.ok(planetService.findAll().stream()
                .map(PlanetOutputDTO::fromDomainEntity)
                .collect(Collectors.toList()));
    }

    @RequestMapping(path="/planet/{id}", method=RequestMethod.GET)
    public ResponseEntity<?> findById(@PathVariable("id") Integer id)
    {
        Planet planet = planetService.findById(id);
        if (planet == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        return ResponseEntity.ok(planet);
    }

    @PostMapping("/planet")
    public ResponseEntity<?> save(@Valid @RequestBody PlanetInputDTO dto)
    {
        Planet planet = PlanetInputDTO.toDomainEntity(dto);
        planet = planetService.createPlanetEntity(planet);
        return ResponseEntity.ok(PlanetOutputDTO.fromDomainEntity(planet));
    }
}
