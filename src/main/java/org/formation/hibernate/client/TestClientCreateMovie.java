package org.formation.hibernate.client;

import org.formation.hibernate.entity.Actor;
import org.formation.hibernate.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestClientCreateMovie {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();


        try {
            entityTransaction.begin();

            Movie movie = new Movie();
            movie.setName("je suis une légende");
            Actor actor = entityManager.find(Actor.class, 1L);
            movie.addActor(actor);
            entityManager.persist(movie);
            entityTransaction.commit();
            System.out.println(movie);

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
    }
}
