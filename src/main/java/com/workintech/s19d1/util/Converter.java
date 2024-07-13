package com.workintech.s19d1.util;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<ActorResponse> actorResponseConvert(List<Actor> actors){
        List<ActorResponse> resp = new ArrayList<>();
        for(Actor act: actors){
            resp.add(actorResponseConvert(act));
        }
        return resp;
    }

    public static ActorResponse actorResponseConvert(Actor actor){
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate(),actor.getMovies());
    }

    public static MovieResponse actorResponseConvert(Movie movie){
        return new MovieResponse(movie.getName(), movie.getDirectorName(), movie.getRating(), movie.getReleaseDate(),movie.getActors());
    }
}
