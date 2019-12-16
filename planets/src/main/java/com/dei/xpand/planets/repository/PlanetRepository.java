package com.dei.xpand.planets.repository;

import com.dei.xpand.planets.domain.Planet;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlanetRepository extends CrudRepository<Planet, Integer>
{
    @Override
    <S extends Planet> S save(S s);

    @Override
    Iterable<Planet> findAll();

    @Override
    void delete(Planet planet);

    @Override
    Optional<Planet> findById(Integer id);
}
