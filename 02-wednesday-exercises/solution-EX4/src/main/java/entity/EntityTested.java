package entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author carol
 */
public class EntityTested {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer c1 = new Customer("John", "Smith");
        Customer c2 = new Customer("Jane", "Doe");
        Customer c3 = new Customer("Carl", "Doe");
        Customer c4 = new Customer("Jenny", "Smith");
        try {
            em.getTransaction().begin();
            em.persist(c1);
            em.persist(c2);
            em.persist(c3);
            em.persist(c4);
            em.getTransaction().commit();
            
            System.out.println(c1.getId());
            System.out.println(c2.getId());
            System.out.println(c3.getId());
            System.out.println(c4.getId());
        } finally {
            em.close();
        }

    }

}
