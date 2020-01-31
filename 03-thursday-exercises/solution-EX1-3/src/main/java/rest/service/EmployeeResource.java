package rest.service;

import com.google.gson.Gson;
import dto.EmployeeDTO;
import entities.Employee;
import facades.EmployeeFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("employee")
public class EmployeeResource {
    
    //NOTE: Change Persistence unit name according to your setup
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu"); 
    EmployeeFacade facade =  EmployeeFacade.getEmployeeFacade(emf);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"succes\"}";
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAll() {
        List<Employee> allEmployees = facade.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = createEmployeeDTOList(allEmployees);
        return new Gson().toJson(employeeDTOs);
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getById(@PathParam("id") int id) {
        Employee employee = facade.getEmployeeById(id);
        return new Gson().toJson(new EmployeeDTO(employee));
    }
    
    @GET
    @Path("/highestpaid")
    @Produces(MediaType.APPLICATION_JSON)
    public String getHighestPaid() {
        List<Employee> employees = facade.getEmployeesWithHighestSalary();
        List<EmployeeDTO> employeeDTOs = createEmployeeDTOList(employees);
        return new Gson().toJson(employeeDTOs);
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByName(@PathParam("name") String name) {
        List<Employee> employees = facade.getEmployeesByName(name);
        List<EmployeeDTO> employeeDTOs = createEmployeeDTOList(employees);
        return new Gson().toJson(employeeDTOs);
    }
    
    private List<EmployeeDTO> createEmployeeDTOList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOs.add(new EmployeeDTO(employee));
        }
        return employeeDTOs;
    }
}
