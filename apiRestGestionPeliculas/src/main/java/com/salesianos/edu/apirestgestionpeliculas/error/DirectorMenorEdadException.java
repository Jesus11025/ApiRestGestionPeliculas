package com.salesianos. edu.apirestgestionpeliculas.error;

public class DirectorMenorEdadException extends RuntimeException {
    public DirectorMenorEdadException(String nombreDirector, int edadAlDirigir) {
        super("El director %s tenía %d años al dirigir la película.  Debe tener al menos 18 años".formatted(nombreDirector, edadAlDirigir));
    }

    public DirectorMenorEdadException() {
        super("El director debe tener al menos 18 años al momento de dirigir la película");
    }
}