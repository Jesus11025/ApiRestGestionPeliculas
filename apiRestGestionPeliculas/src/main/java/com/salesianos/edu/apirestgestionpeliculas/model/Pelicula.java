package com.salesianos.edu.apirestgestionpeliculas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Pelicula {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String genero;
    private LocalDate fechaEstreno;

    @ManyToOne
    @JoinColumn(name = "director_id")
    private Director director;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "peliculas_actores",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @Builder.Default
    private Set<Actor> actors = new HashSet<>();

}
