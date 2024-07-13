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
@Table(name="actor",schema="film")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Actor {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "First name can not be null")
    @Column(name="first_name")
    private String  firstName;

    @NotNull(message = "Last name can not be null")
    @Column(name="last_name")
    private String lastName;

    @NotNull(message = "Gender can not be null")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @NotNull(message = "Birth date can not be null")
    @Column(name="birth_date")
    private LocalDate birthDate;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "movie_actor",
            schema = "film",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private List<Movie> movies;

    public void addMovie(Movie movie){
        if(movies == null){
            movies = new ArrayList<>();
        }
        movies.add(movie);
    }
}
