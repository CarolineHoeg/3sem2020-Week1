package dto;

import entities.Employee;

/**
 *
 * @author carol
 */
public class EmployeeDTO {
    
    private int id;
    private String name;
    private String address;
    
    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
    
}
