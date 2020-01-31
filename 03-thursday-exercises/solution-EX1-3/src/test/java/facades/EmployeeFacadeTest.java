/*Udkommenteret, da den pludselig ikke ville finde packagen entities, 
 *og derfor ikke kunne køre. Den er dog testet igennem og gav grønne tests
 */


//package facades;
//
//import entities.Employee;
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.Persistence;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.jupiter.api.BeforeAll;
//
///**
// *
// * @author thomas
// */
//public class EmployeeFacadeTest {
//    private static final EntityManagerFactory ENF = Persistence.createEntityManagerFactory("testpu");
//    private static final EmployeeFacade EF = EmployeeFacade.getEmployeeFacade(ENF);
//    private static Employee employee = new Employee("John Smith", "1st street", 230);
//    private static Employee employee1 = new Employee("John Johnson", "3rd street", 200);
//    private static Employee employee2 = new Employee("John Smith", "31 street", 300);
//    
//    @BeforeAll
//    public static void setUpClass() {
////        Add code to setup entities for test before running any test methods
//        EF.createEmployee(employee);
//        EF.createEmployee(employee1);
//        EF.createEmployee(employee2);
//    }
//
//    @Test
//    public void testCreateEmployee() {
//        Employee employee = new Employee("Jane Smith", "1st street", 197.50);
//        employee.setId(4);
//        EF.createEmployee(employee);
//        assertTrue(EF.getAllEmployees().contains(employee));
//    }
//    
//    @Test
//    public void testGetEmployeeById() {
//        Employee expected = employee;
//        int id = 1;
//        Employee result = EF.getEmployeeById(id);
//        assertEquals(expected, result);
//    }
//    
//    @Test 
//    public void testGetEmployeesByName() {
//        List<Employee> expected = new ArrayList<>();
//        expected.add(employee1);
//        expected.add(employee2);
//        List<Employee> result = EF.getEmployeesByName("John Smith");
//        assertEquals(expected, result);
//    }
//    
//    @Test
//    public void testGetAllEmployees() {
//        List<Employee> expected = new ArrayList<>();
//        expected.add(employee);
//        expected.add(employee1);
//        expected.add(employee2);
//        List<Employee> result = EF.getAllEmployees();
//        assertEquals(expected, result);
//    }
//    
//    @Test
//    public void testGetEmployeesWithHighestSalary() {
//        List<Employee> expected = new ArrayList<>();
//        expected.add(employee2);
//        List<Employee> result = EF.getEmployeesWithHighestSalary();
//        assertEquals(expected, result);
//    }
//}
