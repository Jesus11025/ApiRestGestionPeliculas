package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas.model.Director;

public record DirectorSimpleDTO(
        String nombre,
        Integer anioNacimiento
) {

    public Director toEntity() {
        return Director.builder()
                .nombre(this.nombre)
                .anioNacimiento(this.anioNacimiento)
                .build();
    }


}
