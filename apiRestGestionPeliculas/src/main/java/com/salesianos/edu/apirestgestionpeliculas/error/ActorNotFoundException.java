package com.salesianos.edu.apirestgestionpeliculas.error;

public class ActorNotFoundException extends EntidadNotFoundException {
    public ActorNotFoundException(String message) {
        super(message);
    }

    public ActorNotFoundException(Long id) {
        super("No hay un actor con ese ID: %d".formatted(id));
    }

    public ActorNotFoundException() {
        super("No hay un actor con esos criterios de busqueda");
    }

}
