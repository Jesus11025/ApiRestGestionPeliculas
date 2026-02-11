package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;
import com.salesianos.edu.apirestgestionpeliculas.validation.DateTimeBetween;
import com.salesianos.edu.apirestgestionpeliculas.validation.UniqueNombre;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record PeliculaRequestDTO(
        @NotBlank(message = "El título no puede estar vacío")
        @UniqueNombre
        String titulo,
        @NotBlank(message = "El género no puede estar vacío")
        String genero,
        @DateTimeBetween(min = "1900-01-01", max = "2025-12-31", message = "La fecha de estreno debe estar entre 1900 y 2025")
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
