package entities;

import facades.BankCustomerFacade;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author carol
 */
public class MakeTestData {
    
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        BankCustomerFacade facade = BankCustomerFacade.getBankCustomerFacade(emf);
        BankCustomer c1 = new BankCustomer("John", "Smith", "122-0", 1000.00,
                23, "info");
        BankCustomer c2 = new BankCustomer("Carl", "Jameson", "55-789", 457.80,
                97, "info");
        BankCustomer c3 = new BankCustomer("Hannah", "Smith", "1452-8", 278.09,
                274, "info");
        BankCustomer c4 = new BankCustomer("Heidi", "Doe", "254-988", 10975.60,
                2, "info");
        BankCustomer c5 = new BankCustomer("James", "Smith", "55-766", 1054.90,
                45, "info");
        facade.addCustomer(c1);
        facade.addCustomer(c2);
        facade.addCustomer(c3);
        facade.addCustomer(c4);
        facade.addCustomer(c5);
    }
    
}
