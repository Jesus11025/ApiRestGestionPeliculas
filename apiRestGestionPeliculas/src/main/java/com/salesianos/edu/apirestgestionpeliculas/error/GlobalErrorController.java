package com.salesianos.edu.apirestgestionpeliculas.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadNotFoundException.class)
    public ProblemDetail handleElementoNotFound (EntidadNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );

        problemDetail.setTitle("Elemento no encontrado");
        problemDetail.setType(
                URI.create("http://dam.salesianos-gestion-peliculas.com/elemento-not-found")
        );
        return problemDetail;
    }

    @ExceptionHandler(PeliculaYaExisteException.class)
    public ProblemDetail handlePeliculaYaExiste(PeliculaYaExisteException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );

        problemDetail.setTitle("La película ya existe");
        problemDetail.setType(
                URI.create("https://dam.salesianos-gestion-peliculas.com/error/pelicula-ya-existe")
        );
        return problemDetail;
    }

    @ExceptionHandler(ActorYaEnRepartoException.class)
    public ProblemDetail handleActorYaEnReparto(ActorYaEnRepartoException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT, ex.getMessage()
        );

        problemDetail.setTitle("El actor ya está asignado");
        problemDetail.setType(
                URI.create("https://dam.salesianos-gestion-peliculas.com/error/actor-ya-en-reparto")
        );
        return problemDetail;
    }

    @ExceptionHandler(DirectorMenorEdadException.class)
    public ProblemDetail handleDirectorMenorEdad(DirectorMenorEdadException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage()
        );

        problemDetail.setTitle("Director  menor de edad");
        problemDetail.setType(
                URI.create("https://dam.salesianos-gestion-peliculas.com/error/director-menor-edad")
        );
        return problemDetail;
    }

    @ExceptionHandler(DirectorConPeliculaException.class)
    public ProblemDetail handleDirectorConPeliculasException(DirectorConPeliculaException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus. CONFLICT,
                ex.getMessage()
        );
        problemDetail.setTitle("Director con películas asignadas");
        problemDetail.setType(URI.create("https://dam.salesianos-gestion-peliculas.com/error/director-con-peliculas"));
        return problemDetail;
    }


}
