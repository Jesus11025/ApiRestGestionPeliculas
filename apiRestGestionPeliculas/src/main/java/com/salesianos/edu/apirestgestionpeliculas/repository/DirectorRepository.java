package com.salesianos.edu.apirestgestionpeliculas.repository;

import com.salesianos.edu.apirestgestionpeliculas.model.Actor;
import com.salesianos.edu.apirestgestionpeliculas.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorRepository extends JpaRepository<Director, Long> {
}
