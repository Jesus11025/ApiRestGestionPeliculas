package com.salesianos.edu.apirestgestionpeliculas.service;

import com.salesianos.edu.apirestgestionpeliculas.dto.ActorRequestDTO;
import com.salesianos.edu.apirestgestionpeliculas.dto.ActorResponseDTO;
import com.salesianos.edu.apirestgestionpeliculas.error.ActorNotFoundException;
import com.salesianos.edu.apirestgestionpeliculas.model.Actor;
import com.salesianos.edu.apirestgestionpeliculas.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public List<ActorResponseDTO> findAll() {
        List<Actor> resultActor = actorRepository.findAll();

        if (resultActor.isEmpty()) {
            throw new ActorNotFoundException();
        }
        return resultActor.stream()
                .map(ActorResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public ActorResponseDTO saveActor(ActorRequestDTO cmd) {
        return ActorResponseDTO.fromEntity(actorRepository.save(cmd.toEntity()));
    }

}
