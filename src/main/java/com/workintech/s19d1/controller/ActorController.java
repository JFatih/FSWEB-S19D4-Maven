package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping
    public List<ActorResponse> findAll(){
        List<Actor> actors = actorService.findAll();
        return Converter.actorResponseConvert(actors);
    }

    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable("id") long id){
        Actor actor = actorService.findById(id);
        return Converter.actorResponseConvert(actor);
    }

    @PostMapping
    public ActorResponse save(@Validated @RequestBody ActorRequest actorRequest){
        Actor actor = actorRequest.getActor();
        List<Movie> movies = actorRequest.getMovies();

        for(Movie mov: movies){
            actor.addMovie(mov);
        }

        Actor savedActor = actorService.save(actor);
        return Converter.actorResponseConvert(savedActor);
    }

    @PutMapping("/{id}")
    public ActorResponse update(@PathVariable("id") long id,@Validated @RequestBody Actor actor){
        Actor foundActor = actorService.findById(id);
        for(Movie mov: foundActor.getMovies()){
            actor.addMovie(mov);
        }
        actor.setId(id);
        Actor savedActor =  actorService.save(actor);
        return Converter.actorResponseConvert(savedActor);
    }

    @DeleteMapping("{id}")
    public ActorResponse delete(@PathVariable("id") long id){
        Actor foundActor = actorService.findById(id);
        actorService.delete(foundActor);
        return Converter.actorResponseConvert(foundActor);
    }
}
