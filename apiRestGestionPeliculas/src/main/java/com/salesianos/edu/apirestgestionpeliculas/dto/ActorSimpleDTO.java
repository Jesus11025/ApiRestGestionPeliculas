package com.salesianos.edu. apirestgestionpeliculas.dto;

import com.salesianos.edu. apirestgestionpeliculas.model.Actor;

public record ActorSimpleDTO(
        Long id,
        String nombre
) {
    public static ActorSimpleDTO fromEntity(Actor actor) {
        return new ActorSimpleDTO(
                actor.getId(),
                actor. getNombre()
        );
    }
}