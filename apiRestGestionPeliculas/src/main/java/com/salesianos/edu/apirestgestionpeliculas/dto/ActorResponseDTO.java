package com.salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu. apirestgestionpeliculas.model.Actor;
import java.util.Set;
import java.util.stream. Collectors;

public record ActorResponseDTO(
        Long id,
        String nombre,
        Set<PeliculaSimpleDTO> peliculas
) {
    public static ActorResponseDTO fromEntity(Actor actor) {
        return new ActorResponseDTO(
                actor.getId(),
                actor.getNombre(),
                actor.getPeliculas().stream()
                        .map(PeliculaSimpleDTO::fromEntity)
                        .collect(Collectors.toSet())
        );
    }
}