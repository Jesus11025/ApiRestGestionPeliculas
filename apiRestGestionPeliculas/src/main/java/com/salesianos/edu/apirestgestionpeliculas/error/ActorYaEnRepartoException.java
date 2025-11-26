package com.salesianos.edu. apirestgestionpeliculas.error;

public class ActorYaEnRepartoException extends RuntimeException {
    public ActorYaEnRepartoException(Long actorId, Long peliculaId) {
        super("El actor con ID %d ya está asignado a la película con ID %d".formatted(actorId, peliculaId));
    }

    public ActorYaEnRepartoException(String nombreActor, String tituloPelicula) {
        super("El actor %s ya está asignado a la película %s".formatted(nombreActor, tituloPelicula));
    }
}