package org.formation.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade ={CascadeType.PERSIST})
    @JoinTable(name = "movie_actor",
    joinColumns ={@JoinColumn(name = "movie_id")},
    inverseJoinColumns ={@JoinColumn(name = "actor_id")})
    private Set<Actor> actors = new HashSet<Actor>();

    public Movie() {
    }

    public Movie(String name) {
        this.name = name;
    }

    public void addActor(Actor actor){
        actors.add(actor);
        actor.getMovies().add(this);
    }

    public void removeActor(Actor actor){
        actors.remove(actor);
        actor.getMovies().remove(this);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                '}';
    }
}
