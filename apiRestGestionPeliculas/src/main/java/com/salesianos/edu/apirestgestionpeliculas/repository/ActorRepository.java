package com.salesianos.edu.apirestgestionpeliculas.repository;

import com.salesianos.edu.apirestgestionpeliculas.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {
}
