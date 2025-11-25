package com.salesianos.edu.apirestgestionpeliculas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "actors")
    private List<Pelicula> peliculas = new ArrayList<>();
}
