package org.formation.hibernate.client;

import org.formation.hibernate.entity.Actor;
import org.formation.hibernate.entity.Movie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class TestClientCreateActor {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();


        try {
            entityTransaction.begin();
            Actor actor = new Actor();
            actor.setName("JE SUIS LE MEILLEUR");
            Movie movie = entityManager.find(Movie.class, 3L);
            movie.addActor(actor);
            entityManager.persist(actor);
            entityTransaction.commit();
            System.out.println(actor);

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
