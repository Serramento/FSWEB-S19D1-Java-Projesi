package com.workintech.s19d1.service;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{

    private final ActorRepository actorRepository;
    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findById(long id) {
        Optional<Actor> foundActor = actorRepository.findById(id);
        if(foundActor.isPresent()){
            return foundActor.get();
        }
        throw new ApiException("actor is not found with id: "+id, HttpStatus.NOT_FOUND);
    }

    @Override
    public Actor save(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void delete(Actor actor) {
        actorRepository.delete(actor);
    }

    @Override
    public Actor update(Actor actor) {
        long id= actor.getId();
        Actor foundActor= findById(id);
        actor.setMovies(foundActor.getMovies());
        return actorRepository.save(actor);
    }
}
