package HRMS.HRManagementSystem.Service;
import HRMS.HRManagementSystem.DTO.EmployeeDTO;
import HRMS.HRManagementSystem.Entity.Department;
import HRMS.HRManagementSystem.Entity.Employee;
import HRMS.HRManagementSystem.Entity.Role;
import HRMS.HRManagementSystem.Repository.DepartmentRepo;
import HRMS.HRManagementSystem.Repository.EmployeeRepo;
import HRMS.HRManagementSystem.Repository.PerformanceReviewRepo;
import HRMS.HRManagementSystem.Repository.RoleRepo;
import HRMS.HRManagementSystem.ServiceImp.EmployeeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeServiceImp {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    DepartmentRepo departmentRepo;
    @Autowired
    PerformanceReviewRepo performanceReviewRepo;
    @Autowired
    RoleRepo roleRepo;
    @Override
    public Employee saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhone(employeeDTO.getPhone());
        employee.setHireDate(employeeDTO.getHireDate());
        Department department = departmentRepo.findById(employeeDTO.getDepartment().getId()).orElse(null);
        employee.setDepartment(department);
        Role role = roleRepo.findById(employeeDTO.getRole().getId()).orElse(null);
        employee.setRole(role);
        return employeeRepo.save(employee);
    }

    @Override
    public List<Employee> getAllEmployess() {
        return employeeRepo.findAll();
    }

    @Override
    public Employee getEmployeeId(long id) {
        Optional<Employee> employee =employeeRepo.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);
        }
    }

    @Override
    public String deleteEmployee(long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if(employee.isPresent()){
            employeeRepo.deleteById(id);
            return "Successfully Deleted";
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);
        }
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        Optional<Employee> employee1 = employeeRepo.findById(id);
        if(employee1.isPresent()){
            Employee employee2 = employee1.get();
            employee2.setFirstName(employee.getFirstName());
            employee2.setLastName(employee.getLastName());
            employee2.setEmail(employee.getEmail());
            employee2.setPhone(employee.getPhone());
            employee2.setRole(employee.getRole());
            employee2.setHireDate(employee.getHireDate());
            employee2.setDepartment(employee.getDepartment());
            return employeeRepo.save(employee2);
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);

        }
    }
}
