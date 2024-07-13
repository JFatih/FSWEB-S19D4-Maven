package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.MovieRequest;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> findAll(){
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Movie findById(@PathVariable long id){
        return movieService.findById(id);
    }

    @PostMapping
    public MovieResponse save(@Validated @RequestBody MovieRequest movieRequest){
        Movie movie= movieRequest.getMovie();
        List<Actor> actors = movieRequest.getActors();

        for(Actor act: actors){
            movie.addActor(act);
        }

        Movie savedMovie = movieService.save(movie);
        return Converter.actorResponseConvert(savedMovie);
    }

    @PutMapping("/{id}")
    public MovieResponse update(@PathVariable("id") long id,@Validated @RequestBody Movie movie){
        Movie foundActor = movieService.findById(id);
        for(Actor act: foundActor.getActors()){
            movie.addActor(act);
        }
        movie.setId(id);
        Movie savedMovie =  movieService.save(movie);
        return Converter.actorResponseConvert(savedMovie);
    }

    @DeleteMapping("{id}")
    public MovieResponse delete(@PathVariable("id") long id){
        Movie foundMovie = movieService.findById(id);
        movieService.delete(foundMovie);
        return Converter.actorResponseConvert(foundMovie);
    }

}
