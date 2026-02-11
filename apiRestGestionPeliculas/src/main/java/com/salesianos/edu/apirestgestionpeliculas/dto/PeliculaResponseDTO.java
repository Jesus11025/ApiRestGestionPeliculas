package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos. edu.apirestgestionpeliculas.model.Pelicula;
import jakarta.validation.constraints.NotBlank;

import java.time. LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record PeliculaResponseDTO(
        Long id,
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,
        @NotBlank(message = "El género no puede estar vacío")
        String genero,
        LocalDate fechaEstreno,
        DirectorSimpleDTO director,
        Set<ActorSimpleDTO> actores
) {
    public static PeliculaResponseDTO fromEntity(Pelicula pelicula) {
        return new PeliculaResponseDTO(
                pelicula.getId(),
                pelicula.getTitulo(),
                pelicula.getGenero(),
                pelicula.getFechaEstreno(),
                pelicula.getDirector() != null
                        ? DirectorSimpleDTO.fromEntity(pelicula.getDirector())
                        : null,
                pelicula.getActors().stream()
                        .map(ActorSimpleDTO::fromEntity)
                        .collect(Collectors.toSet())
        );
    }
}