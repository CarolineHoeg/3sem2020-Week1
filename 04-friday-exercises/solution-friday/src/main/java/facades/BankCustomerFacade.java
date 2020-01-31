package facades;

import dto.BankCustomerDTO;
import entities.BankCustomer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BankCustomerFacade {

    private static BankCustomerFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private BankCustomerFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static BankCustomerFacade getBankCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BankCustomerFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public BankCustomerDTO getCustomerByID(int id) {
        EntityManager em = getEntityManager();
        try {
            BankCustomer customer = em.find(BankCustomer.class, id);
            return new BankCustomerDTO(customer);
        } finally {
            em.close();
        }
    }
    
    public List<BankCustomerDTO> getCustomerByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<BankCustomer> query = em.createQuery("Select c from BankCustomer c"
                    + " WHERE c.firstName = :name", BankCustomer.class);
            query.setParameter("name", name);
            List<BankCustomer> resultList = query.getResultList();
            return createBankCustomerDTOList(resultList);
        } finally {
            em.close();
        }
    }
    
    public BankCustomer addCustomer(BankCustomer customer) {
        EntityManager em = getEntityManager();
        try {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        return customer;
        } finally {
            em.close();
        }
    }
    
    public List<BankCustomer> getAllBankCustomers() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<BankCustomer> query = em.createQuery("Select c from BankCustomer c",
                     BankCustomer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    private List<BankCustomerDTO> createBankCustomerDTOList(List<BankCustomer> customers) {
        List<BankCustomerDTO> customerDTOs = new ArrayList<>();
        for (BankCustomer customer : customers) {
            customerDTOs.add(new BankCustomerDTO(customer));
        }
        return customerDTOs;
    }

}
