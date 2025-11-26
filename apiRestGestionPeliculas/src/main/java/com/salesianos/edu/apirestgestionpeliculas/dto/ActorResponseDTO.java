package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas.model.Actor;
import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;

import java.util.List;
import java.util.Set;

public record ActorResponseDTO(
        Long id,
        String nombre,
        Set<PeliculaSimpleDTO> peliculas

) {

    public Actor toEntity() {
        return Actor.builder()
                .id(this.id)
                .nombre(this.nombre)
                .build();

    }

}
