package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name= "movie", schema = "fsweb")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private Long id;

    @Column(name= "name")
    @NotNull(message= "film adı null olamaz!")
    private String name;

    @Column(name= "director_name")
    @NotNull(message = "yönetmen adı null olamaz!")
    private String directorName;

    @Column(name = "rating")
    private Integer rating;

    @Column(name= "release_date")
    private LocalDate releaseDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "movie_actor", schema = "fsweb",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    @JsonIgnoreProperties("movies")
    private List<Actor> actors;


    public void  addActor(Actor actor){
        if(actors == null){
            actors = new ArrayList<>();
        }
        actors.add(actor);
    }

}
