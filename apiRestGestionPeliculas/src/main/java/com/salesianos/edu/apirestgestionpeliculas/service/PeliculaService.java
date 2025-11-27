package com.salesianos.edu.apirestgestionpeliculas.service;

import com.salesianos.edu.apirestgestionpeliculas.dto.PeliculaRequestDTO;
import com.salesianos.edu.apirestgestionpeliculas.dto.PeliculaResponseDTO;
import com.salesianos.edu.apirestgestionpeliculas.error.*;
import com.salesianos.edu.apirestgestionpeliculas.model.Actor;
import com.salesianos.edu.apirestgestionpeliculas.model.Director;
import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;
import com.salesianos.edu.apirestgestionpeliculas.repository.ActorRepository;
import com.salesianos.edu.apirestgestionpeliculas.repository.DirectorRepository;
import com.salesianos.edu.apirestgestionpeliculas.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;

    public PeliculaResponseDTO save(PeliculaRequestDTO cmd) {
        int mayoriaEdad = 18;
        if(peliculaRepository.existsByTitulo(cmd.titulo())) {
            throw new PeliculaYaExisteException(cmd.titulo());
        }

        Director director = directorRepository.findById(cmd.directoId())
                .orElseThrow(() -> new DirectorNotFoundException(cmd.directoId()));

        int anioEstreno = cmd.fechaEstreno().getYear();
        int edadDirector = LocalDate.now().getYear() - director.getAnioNacimiento();

        if(edadDirector < mayoriaEdad) {
            throw new DirectorMenorEdadException(director.getNombre(), edadDirector);
        }

        Pelicula pelicula = cmd.toEntity();
        pelicula.setDirector(director);

        Pelicula saved = peliculaRepository.save(pelicula);
        return PeliculaResponseDTO.fromEntity(saved);
    }

    public List<Pelicula> findAll() {
        List<Pelicula> result = peliculaRepository.findAll();

        if(result.isEmpty()) {
            throw new PeliculaNotFoundException();
        }

        return result;
    }

    public PeliculaResponseDTO findByID(Long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));

        return PeliculaResponseDTO.fromEntity(pelicula);
    }

    public PeliculaResponseDTO update(Long id, PeliculaResponseDTO cmd) {
        int mayoriaEdad = 18;

        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));

        if(!pelicula.getTitulo().equals(cmd.titulo()) && peliculaRepository.existsByTitulo(cmd.titulo())) {
            throw new PeliculaYaExisteException(cmd.titulo());
        }

        Director director = directorRepository.findById(cmd.director().id())
                .orElseThrow(() -> new DirectorNotFoundException(cmd.director().id()));

        int anioEstreno = cmd.fechaEstreno().getYear();
        int edadDirector = LocalDate.now().getYear() - director.getAnioNacimiento();

        if(edadDirector < mayoriaEdad) {
            throw new DirectorMenorEdadException(director.getNombre(), edadDirector);
        }

        pelicula.setTitulo(cmd.titulo());
        pelicula.setGenero(cmd.genero());
        pelicula.setFechaEstreno(cmd.fechaEstreno());
        pelicula.setDirector(director);

        Pelicula updated = peliculaRepository.save(pelicula);
        return PeliculaResponseDTO.fromEntity(updated);
    }

    public void delete (Long id) {
        Pelicula pelicula = peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNotFoundException(id));
        peliculaRepository.delete(pelicula);
    }

    public PeliculaResponseDTO asignarActor(Long peliculaId, Long actorId) {
        Pelicula pelicula = peliculaRepository.findById(peliculaId)
                .orElseThrow(() -> new PeliculaNotFoundException(peliculaId));

        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ActorNotFoundException(actorId));

        if(pelicula.getActors().contains(actor)) {
            throw new ActorYaEnRepartoException(actorId, peliculaId);
        }

        pelicula.getActors().add(actor);

        Pelicula saved = peliculaRepository.save(pelicula);
        return PeliculaResponseDTO.fromEntity(saved);
    }

}
