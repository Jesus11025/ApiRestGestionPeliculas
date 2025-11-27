package com.salesianos.edu.apirestgestionpeliculas.controller;

import com.salesianos.edu.apirestgestionpeliculas.dto.DirectorRequestDTO;
import com.salesianos.edu.apirestgestionpeliculas.dto.DirectorResponseDTO;
import com.salesianos.edu.apirestgestionpeliculas.dto.PeliculaResponseDTO;
import com.salesianos.edu.apirestgestionpeliculas.service.DirectorService;
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
@RequestMapping("/director")
@RequiredArgsConstructor
@Tag(name = "Director", description = "Controlador de directores, se puede obtener operaciones CRUD")
public class DirectorController {

    private final DirectorService directorService;

    @GetMapping
    @Operation(summary = "Obtener todos los directores", description = "Devuelve un listado con todos los directores")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Se han encontrado directores",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DirectorResponseDTO.class)),
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
                    description = "No se han encontrado directores",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/error/entidad-not-found",
                                        "title": "Entidad no encontrada",
                                        "status": 404,
                                        "detail": "No se ha encontrado ningun director con estos requisitos",
                                        "instance": "/api/v1/directores"
                                    }
                                    """)
                    )
            )
    })
    public List<DirectorResponseDTO> getAll() {
        return directorService.findAll()
                .stream()
                .map(DirectorResponseDTO::fromEntity)
                .toList();

    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un director por ID", description = "Devuelve un director específico")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Director encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DirectorResponseDTO.class),
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
                                        "type": "https://dam.salesianos-gestion-peliculas.com/error/entidad-not-found",
                                        "title": "Entidad no encontrada",
                                        "status": 404,
                                        "detail": "No hay un director con ese ID",
                                        "instance": "/api/v1/directores/1"
                                    }
                                    """)
                    )
            )
    })
    public ResponseEntity<DirectorResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(directorService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo director", description = "Crea un nuevo director")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Director creado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DirectorResponseDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "nombre": "Chris Columbus",
                                        "anioNacimiento": 1958,
                                        "peliculas": []
                                    }
                                    """)
                    )
            )
    })
    public ResponseEntity<DirectorResponseDTO> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos para crear un director",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = DirectorRequestDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "nombre": "Chris Columbus",
                                        "anioNacimiento": 1958
                                    }
                                    """)
                    )
            )
            @RequestBody DirectorRequestDTO dto)
    {
        DirectorResponseDTO created = directorService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un director", description = "Actualiza los datos de un director ya existente")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Director actualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DirectorResponseDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "id": 1,
                                        "nombre": "Chris Columbus",
                                        "anioNacimiento": 1958,
                                        "peliculas": [
                                            {
                                                "id": 1,
                                                "titulo": "Harry Potter",
                                                "genero": "Fansia",
                                                "fechaEstreno": "01-09-1998"
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
                                        "type": "https://dam.salesianos-gestion-peliculas.com/error/entidad-not-found",
                                        "title": "Entidad no encontrada",
                                        "status": 404,
                                        "detail": "No hay un director con ese id",
                                        "instance": "/api/v1/directores/1"
                                    }
                                    """)
                    )
            )
    })
    public ResponseEntity<DirectorResponseDTO> update(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Datos actualizados del director",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = DirectorRequestDTO.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "nombre": "Christopher Nolan",
                                        "anioNacimiento": 1970
                                    }
                                    """)
                    )
            )
            @RequestBody DirectorRequestDTO dto) {
        return ResponseEntity.ok(directorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un director", description = "Elimina un director")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Director eliminado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Director no encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                    {
                                        "type": "https://dam.salesianos-gestion-peliculas.com/error/entidad-not-found",
                                        "title": "Entidad no encontrada",
                                        "status": 404,
                                        "detail": "No hay un director con ese ID",
                                        "instance": "/api/v1/directores/1"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Director tiene películas asociadas",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class),
                            examples = @ExampleObject(value = """
                                {
                                    "type": "https://dam.salesianos-gestion-peliculas.com/error/director-con-peliculas",
                                    "title": "Director con películas asignadas",
                                    "status": 409,
                                    "detail": "No se puede eliminar el director con ID 1 porque tiene películas",
                                    "instance": "/api/v1/directores/1"
                                }
                                """)
                    )
            )
    })
    public ResponseEntity<?> delete(@PathVariable Long id) {
        directorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
