package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas.model.Director;

public record DirectorSimpleDTO(
        Long id,
        String nombre,
        Integer anioNacimiento
) {
    public static DirectorSimpleDTO fromEntity(Director director) {
        return new DirectorSimpleDTO(
                director.getId(),
                director.getNombre(),
                director.getAnioNacimiento()
        );
    }
}
