package com.salesianos.edu.apirestgestionpeliculas.error;

public class PeliculaNotFoundException extends EntidadNotFoundException {
    public PeliculaNotFoundException(String message) {
        super(message);
    }

    public PeliculaNotFoundException(Long id) {
        super("No hay una película con el ID: %d". formatted(id));
    }

    public PeliculaNotFoundException() {
        super("No se encontraron películas con esos criterios de búsqueda");
    }
}