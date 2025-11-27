package com.salesianos.edu.apirestgestionpeliculas.controller;

import com.salesianos.edu.apirestgestionpeliculas.dto.ActorRequestDTO;
import com.salesianos.edu.apirestgestionpeliculas.dto.ActorResponseDTO;
import com.salesianos.edu.apirestgestionpeliculas.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
@RequiredArgsConstructor
@Tag(name = "Actor", description = "Controlador de actores, se puede obtener operaciones CRUD basicas")
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    @Operation(summary = "Obtener los actores", description = "Devuelve todos los actores")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se han encontrado actores",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ActorResponseDTO.class)),
                            examples = @ExampleObject(value = """
                                    [
                                        {
                                            "id": 1,
                                            "nombre": "Daniel Radcliffe",
                                            "peliculas": [
                                                {
                                                    "id": 1,
                                                    "titulo": "Harry Potter y la Piedra Filosofal",
                                                    "genero": "Fantasia",
                                                    "fechaEstreno": "01-09-1998"
                                                }
                                            ]
                                        },
                                        {
                                            "id": 2,
                                            "nombre": "Tom Holland",
                                            "peliculas": []
                                        }
                                    ]
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se han encontrado actores",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/error/entidad-not-found",
                                        "title": "Entidad no encontrada",
                                        "status": 404,
                                        "detail": "No hay actores con ese ID",
                                        "instance": "/api/v1/actores"
                                    }
                                    """)
                    )
            )
    })
    public List<ActorResponseDTO> getAll() {
        return actorService.findAll()
                .stream()
                .map(ActorResponseDTO::fromEntity)
                .toList();
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo actor", description = "Crea un nuevo actor en el sistema")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Actor creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ActorResponseDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "nombre": "Leonardo DiCaprio",
                                        "peliculas": []
                                    }
                                    """)
                    )
            )
    })
    public ResponseEntity<ActorResponseDTO> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para crear un actor",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = ActorRequestDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "nombre": "Daniel Radcliffe"
                                    }
                                    """)
                    )
            )
            @RequestBody ActorRequestDTO dto) {
        ActorResponseDTO created = actorService.saveActor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}