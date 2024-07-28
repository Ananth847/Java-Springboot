package HRMS.HRManagementSystem.Controller;

import HRMS.HRManagementSystem.DTO.EmployeeDTO;
import HRMS.HRManagementSystem.Entity.Employee;
import HRMS.HRManagementSystem.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping("/saveemployee")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDTO employee){
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }
    @GetMapping("/getallemployees")
    public ResponseEntity<List<Employee>> getEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployess(),HttpStatus.OK);
    }
    @GetMapping("/getemployeebyid/{id}")
    public ResponseEntity<Employee> getEmployeeId(@PathVariable long id) {
        Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployeeId(id));
        if(employee.isPresent()){
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);
        }
    }
    @DeleteMapping("/deleteemployee/{id}")
    public String deleteEmployee(@PathVariable long id){
        Optional<Employee> employee = Optional.ofNullable(employeeService.getEmployeeId(id));
        if(employee.isPresent()){
            employeeService.deleteEmployee(id);
            return "Successfully Deleted";
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);
        }
    }
    @PutMapping("/updateemployee")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employee){
        Optional<Employee> employee1 = Optional.ofNullable(employeeService.getEmployeeId(id));
        if(employee1.isPresent()){
            return new ResponseEntity<>( employeeService.updateEmployee(id,employee),HttpStatus.OK);
        }
         else{
            throw new RuntimeException("Employee not found for id: " + id);
        }
    }

}
