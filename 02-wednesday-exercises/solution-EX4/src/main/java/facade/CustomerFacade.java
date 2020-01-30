package facade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author carol
 */
public class CustomerFacade {

    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }

    public Customer findByID(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            Customer customer = em.find(Customer.class, id);
            return customer;
        } finally {
            em.close();
        }
    }

    public List<Customer> findByLastName(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query = em.createQuery("Select c from Customer c"
                    + " WHERE c.lastName = :name", Customer.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int getNumberOfCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query
                    = em.createQuery("Select COUNT(c) from Customer c", Long.class);
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    public List<Customer> allCustomers() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Customer> query
                    = em.createQuery("Select c from Customer c", Customer.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Customer addCustomer(String fName, String lName) {
        Customer customer = new Customer(fName, lName);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
            return customer;
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        CustomerFacade facade = CustomerFacade.getCustomerFacade(emf);
        Customer c1 = facade.addCustomer("John", "Smith");
        Customer c2 = facade.addCustomer("Jane", "Doe");
        Customer c3 = facade.addCustomer("Carl", "Doe");
        Customer c4 = facade.addCustomer("Jenny", "Smith");

        //find by id
        System.out.println("Customer 1: " + facade.findByID(c1.getId()));
        System.out.println("Customer 2: " + facade.findByID(c2.getId()));
        System.out.println("Customer 3: " + facade.findByID(c3.getId()));
        System.out.println("Customer 4: " + facade.findByID(c4.getId()));

        //find by last name
        System.out.println("Last name \"Smith\": " + facade.findByLastName("Smith"));
        System.out.println("Last name \"Doe\": " + facade.findByLastName("Doe"));

        //find all customers
        System.out.println("All customers: " + facade.allCustomers());

        //get number of customers -doesn't work
        System.out.println("Number of customers: " + facade.getNumberOfCustomers());

    }
}
