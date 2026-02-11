package com.salesianos.edu.apirestgestionpeliculas.controller;

import com.salesianos.edu.apirestgestionpeliculas.dto.PeliculaRequestDTO;
import com.salesianos.edu.apirestgestionpeliculas.dto.PeliculaResponseDTO;
import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;
import com.salesianos.edu.apirestgestionpeliculas.repository.PeliculaRepository;
import com.salesianos.edu.apirestgestionpeliculas.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pelicula")
@RequiredArgsConstructor
@Tag(name = "Pelicula", description = "Controlador de Peliculas, se puede obtener las operaciones al respecto")
public class PeliculaController {

    private final PeliculaService peliculaService;
    private final PeliculaRepository peliculaRepository;


    @GetMapping
    @Operation(summary = "Obtener todas las películas", description = "Devuelve un listado de todas las películas")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se han encontrado películas",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PeliculaResponseDTO.class)),
                            examples = @ExampleObject(value = """
                                    [
                                        {
                                            "id": 1,
                                            "titulo": "Harry Potter y la Piedra Filosofal",
                                            "genero": "Fantasía",
                                            "fechaEstreno": "01-09-1998",
                                            "director": {
                                                "id": 1,
                                                "nombre": "Chris Columbus",
                                                "anioNacimiento": 1958
                                            },
                                            "actores": [
                                                {
                                                    "id": 1,
                                                    "nombre": "Daniel Radcliffe"
                                                }
                                            ]
                                        }
                                    ]
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se han encontrado películas",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/errors/entidad-not-found",
                                        "title": "Entidad no encontrada",
                                        "status": 404,
                                        "detail": "No se encontraron películas con esos criterios de búsqueda",
                                        "instance": "/api/v1/peliculas"
                                    }
                                    """)
                    )
            )
    })
    public List<PeliculaResponseDTO> getAll() {
        return peliculaService.findAll()
                .stream()
                .map(PeliculaResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una película por ID", description = "Devuelve una película específica")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Película encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDTO. class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "titulo": "Spiderman 4",
                                        "genero": "Acción",
                                        "fechaEstreno": "02-06-2019",
                                        "director": {
                                            "id": 1,
                                            "nombre": "Jon Watts",
                                            "anioNacimiento": 1981
                                        },
                                        "actores": [
                                            {
                                                "id": 1,
                                                "nombre": "Tom Holland"
                                            },
                                            {
                                                "id": 2,
                                                "nombre": "Zendaya"
                                            }
                                        ]
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Película no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/errors/entidad-not-found",
                                        "title": "Entidad no encontrada",
                                        "status": 404,
                                        "detail": "No hay una película con el ID: 1",
                                        "instance": "/api/v1/peliculas/1"
                                    }
                                    """)
                    )
            )
    })
    public ResponseEntity<PeliculaResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(peliculaService.findByID(id));
    }

    @PostMapping
    @Operation(summary = "Crear nueva película", description = "Crea una película con un director existente")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Película creada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "titulo": "Spiderman 4",
                                        "genero": "Acción",
                                        "fechaEstreno": "02-06-2019",
                                        "director": {
                                            "id": 1,
                                            "nombre": "Jon Watts",
                                            "anioNacimiento": 1981
                                        },
                                        "actores": [
                                            {
                                                "id": 1,
                                                "nombre": "Tom Holland"
                                            },
                                            {
                                                "id": 2,
                                                "nombre": "Zendaya"
                                            }
                                        ]
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Director no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/errors/entidad-not-found",
                                        "title": "Director no encontrado",
                                        "status": 404,
                                        "detail": "No hay un director con el ID: 1",
                                        "instance": "/api/v1/peliculas"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Película con título duplicado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/errors/pelicula-ya-existe",
                                        "title": "Película con nombre ya existente",
                                        "status": 409,
                                        "detail": "Ya existe una película con el título: SpiderMan",
                                        "instance": "/api/v1/peliculas"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Director menor de edad",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas. com/errors/director-menor-edad",
                                        "title": "El director es menor de edad",
                                        "status": 400,
                                        "detail": "El director Jon Watts tenía 17 años al dirigir la película.  Debe tener al menos 18 años",
                                        "instance": "/api/v1/peliculas"
                                    }
                                    """)
                    )
            )
    })
    public ResponseEntity<PeliculaResponseDTO> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos necesarios para crear una nueva película",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = PeliculaRequestDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "titulo": "SpiderMan",
                                        "genero": "Acción",
                                        "fechaEstreno": "02-06-2019",
                                        "directorId": 1
                                    }
                                    """)
                    )
            )
            @Valid @RequestBody PeliculaRequestDTO cmd)
    {
        PeliculaResponseDTO created = peliculaService.save(cmd);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una película", description = "Actualiza los datos de una película existente")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Película actualizada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Elemento no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/errors/entidad-not-found",
                                        "title": "Elemento no encontrado",
                                        "status": 404,
                                        "detail": "No hay un elemento con el ID: 1",
                                        "instance": "/api/v1/peliculas"
                                    }
                                    """)

                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Título duplicado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/errors/pelicula-ya-existe",
                                        "title": "Película con nombre ya existente",
                                        "status": 409,
                                        "detail": "Ya existe una película con el título: SpiderMan",
                                        "instance": "/api/v1/peliculas"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Director menor de edad",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas. com/errors/director-menor-edad",
                                        "title": "El director es menor de edad",
                                        "status": 400,
                                        "detail": "El director Jon Watts tenía 17 años al dirigir la película.  Debe tener al menos 18 años",
                                        "instance": "/api/v1/peliculas"
                                    }
                                    """)
                    )
            )
    })
    public ResponseEntity<PeliculaResponseDTO> update(@PathVariable Long id, @RequestBody PeliculaRequestDTO cmd) {
        return ResponseEntity.ok(peliculaService.update(id, cmd));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una película", description = "Elimina una película por su ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Película eliminada exitosamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Película no encontrada",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class)
                    )
            )
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
//        System.out.println(id);
//        Pelicula p = peliculaRepository.findById(id).get();
//        System.out.println(p);
        peliculaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{peliculaId}/actores/{actorId}")
    @Operation(summary = "Asignar un actor a una película", description = "Añade un actor al reparto de una película")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Actor asignado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PeliculaResponseDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "titulo": "Spiderman 4",
                                        "genero": "Acción",
                                        "fechaEstreno": "02-06-2019",
                                        "director": {
                                            "id": 1,
                                            "nombre": "Jon Watts",
                                            "anioNacimiento": 1981
                                        },
                                        "actores": [
                                            {
                                                "id": 1,
                                                "nombre": "Tom Holland"
                                            },
                                            {
                                                "id": 2,
                                                "nombre": "Zendaya"
                                            }
                                        ]
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Película o Actor no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/errors/entidad-not-found",
                                        "title": "Entidad no encontrada",
                                        "status": 404,
                                        "detail": "No hay una pelicula con el ID: 5",
                                        "instance": "/api/v1/peliculas/5"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "El actor ya está asignado a la película",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/errors/actor-ya-en-reparto",
                                        "title": "El actor ya está asignado",
                                        "status": 409,
                                        "detail": "El actor con ID 3 ya está asignado a la película con ID 5",
                                        "instance": "/api/v1/peliculas/5/actores/3"
                                    }
                                    """)
                    )
            )
    })
    public ResponseEntity<PeliculaResponseDTO> asignarActor(@PathVariable Long peliculaId, @PathVariable Long actorId) {
        return ResponseEntity.ok(peliculaService.asignarActor(peliculaId, actorId));
    }
}
