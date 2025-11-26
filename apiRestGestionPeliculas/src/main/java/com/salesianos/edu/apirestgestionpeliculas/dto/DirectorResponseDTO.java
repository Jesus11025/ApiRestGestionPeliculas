package com.salesianos. edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas. model.Director;
import java. util.Set;
import java. util.stream.Collectors;

public record DirectorResponseDTO(
        Long id,
        String nombre,
        Integer anioNacimiento,
        Set<PeliculaSimpleDTO> peliculas
) {
    public static DirectorResponseDTO fromEntity(Director director) {
        return new DirectorResponseDTO(
                director. getId(),
                director.getNombre(),
                director.getAnioNacimiento(),
                director. getPeliculas().stream()
                        .map(PeliculaSimpleDTO::fromEntity)
                        .collect(Collectors.toSet())
        );
    }
}