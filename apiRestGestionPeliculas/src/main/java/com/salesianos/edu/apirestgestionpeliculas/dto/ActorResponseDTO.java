package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas.model.Actor;
import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;

import java.util.List;

public record ActorResponseDTO(
        Long id,
        String nombre,
        List<PeliculaSimpleDTO> peliculas

) {

    public Actor toEntity(List<Pelicula> listaPeli) {
        return Actor.builder()
                .id(this.id)
                .nombre(this.nombre)
                .peliculas(listaPeli)
                .build();

    }

}
