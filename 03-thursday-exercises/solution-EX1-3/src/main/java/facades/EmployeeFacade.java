package facades;

import entities.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class EmployeeFacade {

    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private EmployeeFacade() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public Employee createEmployee(Employee employee) {
        EntityManager em = getEntityManager();
        try {
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        return employee;
        } finally {
            em.close();
        }
    }

    public Employee getEmployeeById(int id) {
        EntityManager em = getEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e"
                    + " WHERE e.name = :name", Employee.class);
            query.setParameter("name", name);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployees() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e",
                     Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Employee> getEmployeesWithHighestSalary() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Employee> query = em.createQuery("Select e from Employee e"
                    + " WHERE e.salary = (Select MAX(e.salary) from Employee e)", Employee.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    
}
