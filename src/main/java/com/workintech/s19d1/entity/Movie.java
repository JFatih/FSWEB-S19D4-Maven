package com.workintech.s19d1.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="movie",schema="film")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name can not be null")
    private String name;

    @NotNull(message = "Director name can not be null")
    @Column(name = "director_name")
    private String directorName;

    @NotNull(message = "Rating can not be null")
    private int rating;

    @NotNull(message = "Release date can not be null")
    @Column(name="release_date")
    private LocalDate releaseDate;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "movie_actor",schema="film",
    joinColumns = @JoinColumn(name="movie_id"),
    inverseJoinColumns = @JoinColumn(name="actor_id"))
    private List<Actor> actors;

    public void addActor(Actor actor){
        if(actors == null){
            actors = new ArrayList<>();
        }
        actors.add(actor);
    }
}