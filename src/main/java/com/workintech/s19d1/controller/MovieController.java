package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.dto.MovieRequest;
import com.workintech.s19d1.dto.MovieResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.MovieService;
import com.workintech.s19d1.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponse> findAll() {
        List<Movie> movieList = movieService.findAll();
        return Converter.movieResponseConvert(movieList);
    }

    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable long id) {
        return Converter.movieResponseConvert(movieService.findById(id));
    }

    @PostMapping
    public MovieResponse save(@RequestBody MovieRequest movieRequest){
        List<Actor> actorList= movieRequest.getActorList();
        Movie movie= movieRequest.getMovie();
        for(Actor actor: actorList){
            movie.addActor(actor);
        }
        movieService.save(movie);

        return Converter.movieResponseConvert(movie);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        Movie foundMovie = movieService.findById(id);
        movieService.delete(foundMovie);
    }
}
