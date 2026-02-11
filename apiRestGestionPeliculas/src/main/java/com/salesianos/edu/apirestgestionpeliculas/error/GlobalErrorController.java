package com.salesianos.edu.apirestgestionpeliculas.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail result = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Error de validación en los campos");

        List<ApiValidationSubError> subErrors = ex.getAllErrors().stream()
                .map(ApiValidationSubError::from)
                .toList();

        result.setProperty("invalid-params", subErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(result);
    }

    @Builder
        record ApiValidationSubError(
            String object,
            String message,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            String field,
            @JsonInclude(JsonInclude.Include.NON_NULL)
            Object rejectedValue
    ) {
        public ApiValidationSubError(String object, String message) {
            this(object, message, null, null);
        }

        public static ApiValidationSubError from(ObjectError error) {
            ApiValidationSubError result = null;

            if (error instanceof FieldError fieldError) {
                result = ApiValidationSubError.builder()
                        .object(fieldError.getObjectName())
                        .message(fieldError.getDefaultMessage())
                        .field(fieldError.getField())
                        .rejectedValue(fieldError.getRejectedValue())
                        .build();
            } else {
                result = ApiValidationSubError.builder()
                        .object(error.getObjectName())
                        .message(error.getDefaultMessage())
                        .build();
            }
            return  result;
        }
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
