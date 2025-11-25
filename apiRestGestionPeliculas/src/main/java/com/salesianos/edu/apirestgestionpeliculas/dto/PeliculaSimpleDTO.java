package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;

import java.time.LocalDate;

public record PeliculaSimpleDTO(
        String titulo,
        String genero,
        LocalDate fechaEstreno
) {

    public Pelicula toEntity() {

    }
}
