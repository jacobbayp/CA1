/*
package facades;

import entities.JokeEntity;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;


public class JokeFacade {

    private static JokeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private JokeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     
    public static JokeFacade getJokeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new JokeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public JokeEntity createJoke(String theJoke, String reference, String type) {
        EntityManager em = emf.createEntityManager();
        JokeEntity joke = new JokeEntity(theJoke, reference, type);
        try {
            em.getTransaction().begin();
            em.persist(joke);
            em.getTransaction().commit();
            return joke;
        } finally {
            em.close();
        }
    }

    public List<JokeEntity> getAllJokes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<JokeEntity> query
                    = em.createQuery("SELECT j FROM Joke j", JokeEntity.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public JokeEntity getJokeById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            JokeEntity joke = em.find(JokeEntity.class, id);
            return joke;
        } finally {
            em.close();
        }
    }



   
    public long getJokeCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long jokeCount = (long) em.createQuery("SELECT COUNT(j) FROM Joke j").getSingleResult();
            return jokeCount;
        } finally {
            em.close();
        }

    }
}

*/