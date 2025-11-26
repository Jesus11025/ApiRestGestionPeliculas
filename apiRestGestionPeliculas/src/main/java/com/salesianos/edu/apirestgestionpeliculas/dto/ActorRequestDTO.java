package com. salesianos.edu.apirestgestionpeliculas.dto;

import com.salesianos.edu.apirestgestionpeliculas.model.Actor;

public record ActorRequestDTO(
        String nombre
) {
    public Actor toEntity() {
        return Actor.builder()
                .nombre(this.nombre)
                .build();
    }
}