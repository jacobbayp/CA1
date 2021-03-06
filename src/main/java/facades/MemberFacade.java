package facades;

import dtos.MemberDTO;
import entities.MemberEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class MemberFacade {

    private static MemberFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private MemberFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static MemberFacade getMemberFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MemberFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public MemberDTO create(MemberDTO rm){
        MemberEntity rme = new MemberEntity(rm.getName(), rm.getStudentID(), rm.getFavouriteColor());
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new MemberDTO(rme);
    }
    public MemberDTO getById(long id){
        EntityManager em = emf.createEntityManager();
        return new MemberDTO(em.find(MemberEntity.class, id));
    }
    
    //TODO Remove/Change this before use
    public long getRowCount(){
        EntityManager em = emf.createEntityManager();
        try{
            long renameMeCount = (long)em.createQuery("SELECT COUNT(r) FROM MemberEntity r").getSingleResult();
            return renameMeCount;
        }finally{  
            em.close();
        }
        
    }
    
    public List<MemberDTO> getAllMembers(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<MemberEntity> query = em.createQuery("SELECT Members FROM MemberEntity Members", MemberEntity.class);
        List<MemberEntity> rms = query.getResultList();
        return MemberDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        MemberFacade fe = getMemberFacade(emf);
        fe.getAllMembers().forEach(dto->System.out.println(dto));
    }

 
}
