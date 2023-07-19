package org.formation.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "actors", cascade = CascadeType.PERSIST)
    private Set<Movie> movies = new HashSet<Movie>();

    public Actor() {
    }

    public Actor(String name) {
        this.name = name;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        movie.getActors().add(this);
    }

    public void removeMovie(Movie movie){
        movies.remove(movie);
        movie.getActors().remove(this);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                '}';
    }
}
