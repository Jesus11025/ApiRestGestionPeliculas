# API REST- GESTION DE PELICULAS - JESÚS ZAMORANO

Proyecto de api rest para la gestión de peliculas, directores y actores

## Descripción

Este proyecto implementa una API Rest que realiza lo siguiente:
 - Gestiona catalogos de peliculas con cruds
 - Administra directores con cruds
 - Gestiona actores con cruds basicos
 - Asigna actores a peliculas
 - Validaciones varias
 - Gestión de errores
 - Docuemntación con OpenAPI y swagger

## Tecnologias utilizadas

 - Java 17
 - Spring Boot
 - Spring Data JPA
 - H2 Database
 - Lombok
 - OpenAPI
 - Maven

### Pasos para ejecutar

1. Clonar repositorio
2. Compilar proyecto
3. Ejecutar aplicación

Se podrá acceder con la siguiente url: http://localhost:8080

Se puede acceder a la documentación de Swagger en la siguiente url: http://localhost:8080/swagger-ui.html

Se puede acceder a OpenApi con la siguiente url: http://localhost:8080/api-docs

## Endpoints 

### Peliculas
| Método | Endpoint | Descripción | Códigos |
|--------|----------|-------------|---------|
| GET | `/api/v1/peliculas` | Listar todas las películas | 200, 404 |
| GET | `/api/v1/peliculas/{id}` | Obtener película por ID (con reparto) | 200, 404 |
| POST | `/api/v1/peliculas` | Crear película | 201, 400, 404, 409 |
| PUT | `/api/v1/peliculas/{id}` | Actualizar película | 200, 400, 404, 409 |
| DELETE | `/api/v1/peliculas/{id}` | Eliminar película | 204, 404 |
| POST | `/api/v1/peliculas/{peliculaId}/actores/{actorId}` | Asignar actor a película | 200, 404, 409 |

### Directores
| Método | Endpoint | Descripción | Códigos |
|--------|----------|-------------|---------|
| GET | `/api/v1/directores` | Listar todos los directores | 200, 404 |
| GET | `/api/v1/directores/{id}` | Obtener director por ID | 200, 404 |
| POST | `/api/v1/directores` | Crear director | 201 |
| PUT | `/api/v1/directores/{id}` | Actualizar director | 200, 404 |
| DELETE | `/api/v1/directores/{id}` | Eliminar director | 204, 404 |

### Actores
| Método | Endpoint | Descripción | Códigos |
|--------|----------|-------------|---------|
| GET | `/api/v1/actores` | Listar todos los actores | 200, 404 |
| POST | `/api/v1/actores` | Crear actor | 201 |

## Gestión de Errores

La API implementa manejo global de excepciones siguiendo el estándar **RFC 7807 (ProblemDetail)**:

| Código | Excepción | Descripción |
|--------|-----------|-------------|
| **404** | `EntidadNoEncontradaException` | Película, Director o Actor no encontrado |
| **409** | `PeliculaYaExisteException` | Ya existe una película con ese título |
| **409** | `ActorYaEnRepartoException` | El actor ya está asignado a esa película |
| **400** | `DirectorMenorEdadException` | El director tenía menos de 18 años al dirigir la película |

## 🗂️ Estructura del Proyecto

```
src/main/java/com/salesianos/edu/apirestgestionpeliculas/
├── config/              # Configuración (OpenAPI, datos iniciales)
├── controller/          # Controladores REST
├── dto/                 # DTOs (Request, Response, Simple)
├── error/               # Excepciones personalizadas y manejador global
├── model/               # Entidades JPA (Director, Pelicula, Actor)
├── repository/          # Repositorios Spring Data JPA
└── service/             # Lógica de negocio
```

## 📝 Licencia

Proyecto realizado por Jesús Zamorano Rodríguez estudiante de **Desarrollo de Aplicaciones Multiplataforma** en **Salesianos Triana**.

---

**Fecha de entrega:** 28 de Noviembre de 2024  
**Asignatura:** Programación de Servicios y Procesos  
**Curso:** 2º DAM 2024/2025