package com.salesianos.edu.apirestgestionpeliculas.service;


import com.salesianos.edu.apirestgestionpeliculas.dto.DirectorRequestDTO;
import com.salesianos.edu.apirestgestionpeliculas.dto.DirectorResponseDTO;
import com.salesianos.edu.apirestgestionpeliculas.error.DirectorConPeliculaException;
import com.salesianos.edu.apirestgestionpeliculas.error.DirectorNotFoundException;
import com.salesianos.edu.apirestgestionpeliculas.model.Director;
import com.salesianos.edu.apirestgestionpeliculas.repository.DirectorRepository;
import com.salesianos.edu.apirestgestionpeliculas.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;
    private final PeliculaRepository peliculaRepository;

    public DirectorResponseDTO save(DirectorRequestDTO cmd) {
        return DirectorResponseDTO.fromEntity(directorRepository.save(cmd.toEntity()));
    }

    public List<Director> findAll() {
        List<Director> result = directorRepository.findAll();

        if(result.isEmpty()) {
            throw new DirectorNotFoundException();
        }

        return result;
    }

    public DirectorResponseDTO findById(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));
        return DirectorResponseDTO.fromEntity(director);
    }

    public DirectorResponseDTO update(Long id, DirectorRequestDTO dto) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));

        director.setNombre(dto.nombre());
        director.setAnioNacimiento(dto.anioNacimiento());

        Director updated = directorRepository.save(director);
        return DirectorResponseDTO.fromEntity(updated);
    }

    public void delete(Long id) {
        Director director = directorRepository.findById(id)
                .orElseThrow(() -> new DirectorNotFoundException(id));

        long peliculasCount = peliculaRepository.countByDirectorId(id);

        if (peliculasCount > 0) {
            throw new DirectorConPeliculaException(id);
        }

        directorRepository.delete(director);
    }

}
