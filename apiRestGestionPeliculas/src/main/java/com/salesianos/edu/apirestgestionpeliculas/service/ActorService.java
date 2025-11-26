package com.salesianos.edu.apirestgestionpeliculas.service;

import com.salesianos.edu.apirestgestionpeliculas.dto.ActorResponseDTO;
import com.salesianos.edu.apirestgestionpeliculas.error.ActorNotFoundException;
import com.salesianos.edu.apirestgestionpeliculas.model.Actor;
import com.salesianos.edu.apirestgestionpeliculas.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public List<Actor> getAll() {
        List<Actor> resultActor = actorRepository.findAll();

        if (resultActor.isEmpty()) {
            throw new ActorNotFoundException();
        }
        return resultActor;
    }

    public Actor saveActor(ActorResponseDTO cmd, Actor actor) {
        return actorRepository.save(
                Actor.builder()
                        .id(cmd.id())
                        .nombre(cmd.nombre())
                        .build()
        );
    }

}
