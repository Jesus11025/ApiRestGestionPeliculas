package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;

import java.time.LocalDate;

public record PeliculaRequestDTO(
        String titulo,
        String genero,
        LocalDate fechaEstreno,
        Long directoId
) {

    public Pelicula toEntity() {
        return Pelicula.builder()
                .titulo(this.titulo)
                .genero(this.genero)
                .fechaEstreno(this.fechaEstreno)
                .build();
    }


}
