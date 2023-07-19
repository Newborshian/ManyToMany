package org.formation.hibernate.client;

import org.formation.hibernate.entity.Actor;
import org.formation.hibernate.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestClientFindMovieOnActor {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        Actor actor = entityManager.find(Actor.class, 1L);


        try {
            entityTransaction.begin();


            actor.getMovies().size();

            entityTransaction.commit();


        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
        }
        System.out.println(actor.getMovies());
    }
}
