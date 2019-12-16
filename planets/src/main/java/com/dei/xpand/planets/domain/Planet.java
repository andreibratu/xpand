package com.dei.xpand.planets.domain;

import javax.persistence.*;

@Entity
@Table(name = "planets")
public class Planet
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String imagePath;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PlanetStatus status;

    public Planet() { }

    public String getName() { return name; }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public PlanetStatus getStatus() {
        return status;
    }

    public void setName(String name) { this.name = name; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public void setStatus(PlanetStatus status) { this.status = status; }
}
