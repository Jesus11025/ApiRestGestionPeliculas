package com.salesianos.edu.apirestgestionpeliculas.repository;

import com.salesianos.edu.apirestgestionpeliculas.model.Actor;
import com.salesianos.edu.apirestgestionpeliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {
}
