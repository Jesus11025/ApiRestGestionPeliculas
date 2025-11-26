package com.salesianos.edu.apirestgestionpeliculas.service;

import com.salesianos.edu.apirestgestionpeliculas.dto.PeliculaResponseDTO;
import com.salesianos.edu.apirestgestionpeliculas.error.DirectorNotFoundException;
import com.salesianos.edu.apirestgestionpeliculas.error.PeliculaNotFoundException;
import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;
import com.salesianos.edu.apirestgestionpeliculas.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;

    public List<PeliculaResponseDTO> findAll() {
        List<Pelicula> result = peliculaRepository.findAll();

        if (result.isEmpty()) {
            throw new PeliculaNotFoundException();
        }

        return result.stream()
                .map(PeliculaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

}
