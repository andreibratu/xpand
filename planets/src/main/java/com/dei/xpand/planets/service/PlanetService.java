package com.dei.xpand.planets.service;

import com.dei.xpand.planets.domain.Planet;
import com.dei.xpand.planets.repository.PlanetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetService
{
    private final PlanetRepository planetRepository;

    public PlanetService(PlanetRepository planetRepository) { this.planetRepository = planetRepository; }

    public List<Planet> findAll() { return (List<Planet>) planetRepository.findAll(); }

    public Planet findById(Integer id) { return planetRepository.findById(id).orElse(null); }

    public Planet createPlanetEntity(Planet planet) { return planetRepository.save(planet); }
}
