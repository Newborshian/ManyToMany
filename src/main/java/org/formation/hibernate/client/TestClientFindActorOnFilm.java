package org.formation.hibernate.client;

import org.formation.hibernate.entity.Actor;
import org.formation.hibernate.entity.Movie;

import javax.persistence.*;

public class TestClientFindActorOnFilm {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("test");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        TypedQuery<Movie> typedQuery = entityManager.createQuery("select m FROM Movie m  JOIN  FETCH  m.actors WHERE m.id = :id", Movie.class);
        typedQuery.setParameter("id", 1L);
        Movie m = typedQuery.getSingleResult();

        try {

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
        System.out.println(m.getActors());
    }
}
