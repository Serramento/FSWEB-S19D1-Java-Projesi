package com.workintech.s19d1.util;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static List<ActorResponse> actorResponseConvert(List<Actor> allActors) {
        List<ActorResponse> actors = new ArrayList<>();
        for (Actor actor : allActors) {
            actors.add(new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate(), actor.getMovies()));
        }
        return actors;
    }

    public static ActorResponse actorResponseConvert(Actor actor) {
        return new ActorResponse(actor.getId(), actor.getFirstName(), actor.getLastName(), actor.getBirthDate(), actor.getMovies());

    }

    public static List<MovieResponse> movieResponseConvert(List<Movie> movieList) {
        List<MovieResponse> movieResponses = new ArrayList<>();
        for(Movie movie: movieList){
            movieResponses.add(new MovieResponse(movie.getId(), movie.getName(), movie.getRating()));
        }
        return movieResponses;
    }

    public static MovieResponse movieResponseConvert(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getName(), movie.getRating());
    }
}
