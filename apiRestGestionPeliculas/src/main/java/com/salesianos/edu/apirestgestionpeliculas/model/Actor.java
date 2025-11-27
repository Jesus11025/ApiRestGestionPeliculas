package com.salesianos.edu.apirestgestionpeliculas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Actor {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;

    @ManyToMany(mappedBy = "actors", fetch = FetchType.EAGER)
    private Set<Pelicula> peliculas = new HashSet<>();
}
