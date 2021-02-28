package facades;

import entities.MemberEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.EMF_Creator;


public class MemberFacadeTest {
    
    private static EntityManagerFactory emf;
    private static MemberFacade facade;
    private static MemberEntity m1, m2, m3;
    
    public MemberFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = MemberFacade.getMemberFacade(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("MemberEntity.deleteAllRows").executeUpdate();
            em.persist(new MemberEntity("Jacob", "cph-jp385","blå"));
           

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    @AfterEach
    public void tearDown() {
        
    }

     @Test
    void getAll() {
        List<MemberEntity> list = new ArrayList<>();
        list.add(m1);
        assertEquals(1, facade.getAllMembers().size());
    }
}

    

    
