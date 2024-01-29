package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.dto.ActorResponse;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.service.ActorService;
import com.workintech.s19d1.util.Converter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {
    private final ActorService actorService;
    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<ActorResponse> findAll() {
        List<Actor> allActors = actorService.findAll();
        return Converter.actorResponseConvert(allActors);
    }


    @GetMapping("/{id}")
    public ActorResponse findById(@PathVariable long id) {
        return Converter.actorResponseConvert(actorService.findById(id));
    }

    @PostMapping
    public ActorResponse save(@Validated @RequestBody ActorRequest actorRequest) {
        List<Movie> movies = actorRequest.getMovies();
        Actor actor = actorRequest.getActor();
        for (Movie movie : movies) {
            actor.addMovie(movie);
        }
        actorService.save(actor);
        return Converter.actorResponseConvert(actor);
    }

    @PutMapping("/{id}")
    public ActorResponse update(@RequestBody Actor actor, @PathVariable Long id) {
        actor.setId(id);
        Actor updated= actorService.update(actor);
        return Converter.actorResponseConvert(actor);
    }

    @DeleteMapping("/{id}")
    public ActorResponse delete(@PathVariable long id) {
        Actor foundActor = actorService.findById(id);
        actorService.delete(foundActor);
        return Converter.actorResponseConvert(foundActor);
    }


}
