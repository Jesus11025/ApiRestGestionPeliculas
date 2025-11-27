package com.salesianos.edu.apirestgestionpeliculas.error;

public class DirectorConPeliculaException extends RuntimeException {
    public DirectorConPeliculaException(Long id) {
        super("No se puede eliminar el director con ID " + id + " porque tiene películas asociadas");
    }
}
