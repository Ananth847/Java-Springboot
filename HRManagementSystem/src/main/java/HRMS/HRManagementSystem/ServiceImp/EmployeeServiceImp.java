package HRMS.HRManagementSystem.ServiceImp;

import HRMS.HRManagementSystem.DTO.EmployeeDTO;
import HRMS.HRManagementSystem.Entity.Department;
import HRMS.HRManagementSystem.Entity.Employee;

import java.util.List;

public interface EmployeeServiceImp {
    public Employee saveEmployee(EmployeeDTO employeeDTO);
    public List<Employee> getAllEmployess();
    public Employee getEmployeeId(long id);
    public String deleteEmployee(long id);
    public Employee updateEmployee(long id,Employee employee);
}
