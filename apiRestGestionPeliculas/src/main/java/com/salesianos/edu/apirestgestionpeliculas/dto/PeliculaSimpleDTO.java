package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;

import java.time.LocalDate;

public record PeliculaSimpleDTO(
        Long id,
        String titulo,
        String genero,
        LocalDate fechaEstreno
) {
    public static PeliculaSimpleDTO fromEntity(Pelicula pelicula) {
        return new PeliculaSimpleDTO(
                pelicula. getId(),
                pelicula. getTitulo(),
                pelicula.getGenero(),
                pelicula.getFechaEstreno()
        );
    }
}
