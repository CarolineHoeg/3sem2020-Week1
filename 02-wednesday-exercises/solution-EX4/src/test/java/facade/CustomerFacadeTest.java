package facade;

import entity.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author carol
 */
public class CustomerFacadeTest {

    @Test
    public void testFindByID() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testpu");
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        int id = 1;
        Customer c1 = new Customer("John", "Smith");
        c1.setId(id);
        Customer expResult = c1;
        int resultId = c1.getId();
        Customer result = instance.findByID(id);
        assertEquals(expResult, result);
        assertEquals(id, resultId);
    }

    @Test
    public void testFindByLastName() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testpu");
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        String name = "Doe";
        Customer c1 = instance.addCustomer("John", "Sunshine");
        Customer c2 = instance.addCustomer("Jane", "Doe");
        Customer c3 = instance.addCustomer("Carl", "Doe");
        List<Customer> expResult = new ArrayList<>();
        expResult.add(c2);
        expResult.add(c3);
        List<Customer> result = instance.findByLastName(name);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetNumberOfCustomers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testpu");
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        int expResult = 5;
        int result = instance.getNumberOfCustomers();
        assertEquals(expResult, result);
    }

    @Test
    public void testAllCustomers() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testpu");
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        Customer c = new Customer("John", "Smith");
        c.setId(1);
        Customer c1 = new Customer("John", "Sunshine");
        c1.setId(2);
        Customer c2 = new Customer("Jane", "Doe");
        c2.setId(3);
        Customer c3 = new Customer("Carl", "Doe");
        c3.setId(4);
        Customer c4 = new Customer("Gina", "Smith");
        c4.setId(5);
        List<Customer> expResult = new ArrayList<>();
        expResult.add(c);
        expResult.add(c1);
        expResult.add(c2);
        expResult.add(c3);
        expResult.add(c4);
        List<Customer> result = instance.allCustomers();
        assertEquals(expResult, result);
    }

    @Test
    public void testAddCustomer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testpu");
        CustomerFacade instance = CustomerFacade.getCustomerFacade(emf);
        String fName = "Gina";
        String lName = "Smith";
        Customer expResult = new Customer(fName, lName);
        expResult.setId(5);
        Customer result = instance.addCustomer(fName, lName);
        assertEquals(expResult, result);
    }
    
}
