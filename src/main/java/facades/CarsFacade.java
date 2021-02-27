package facades;

import entities.CarEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;


public class CarsFacade {
    
    private static CarsFacade instance;
    private static EntityManagerFactory emf;


    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static CarsFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CarsFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void deleteAllCars() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Car.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public long getCarsCount() {
        EntityManager em = emf.createEntityManager();
        try {
            long carCount = (long) em.createQuery("SELECT COUNT(c) FROM Car c").getSingleResult();
            return carCount;
        } finally {
            em.close();
        }
    }

    public CarEntity getCarsById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            CarEntity car = em.find(CarEntity.class, id); 
            return car;
        } finally {
            em.close();
        }
    }

    public List<CarEntity> getCarsByModel(String model) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Car.getByModel");
            query.setParameter("model", model);
            List<CarEntity> carList = query.getResultList();
            return carList;
        } finally {
            em.close();
        }
    }

    public List<CarEntity> getAllCars() {

        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createNamedQuery("Car.getAll");
            List<CarEntity> cars = query.getResultList();
            return cars;
        } finally {
            em.close();
        }
    }

    public void populateDB() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new CarEntity(1997, "Ford", "E350", "Jonas", 3000));
            em.persist(new CarEntity(2000, "Chevy", "Venture", "Jacob", 5000));
            em.persist(new CarEntity(2000, "Chevy", "Venture", "Casper", 4900));
            em.persist(new CarEntity(1991, "Jeep", "Grand Cherokee", "Jacob", 4799));
            em.persist(new CarEntity(2005, "Volvo", "V70", "Casper", 44799));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}