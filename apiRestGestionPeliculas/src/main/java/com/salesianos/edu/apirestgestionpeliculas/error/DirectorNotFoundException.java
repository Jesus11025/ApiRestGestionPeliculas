package com.salesianos.edu.apirestgestionpeliculas.error;

public class DirectorNotFoundException extends EntidadNotFoundException {
    public DirectorNotFoundException(String message) {
        super(message);
    }

    public DirectorNotFoundException(Long id) {
        super("No hay un director con el ID: %d".formatted(id));
    }

    public DirectorNotFoundException() {
        super("No se encontraron directores con esos criterios de búsqueda");
    }
}