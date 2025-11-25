package com.salesianos.edu.apirestgestionpeliculas.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ActorNotFoundException.class)
    public ProblemDetail handlecActorNotFound (ActorNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );

        problemDetail.setTitle("Actor no encontrado");
        problemDetail.setType(
                URI.create("http://dam.salesianos-gestion-peliculas.com/actor-not-found")
        );

        return problemDetail;
    }



}
