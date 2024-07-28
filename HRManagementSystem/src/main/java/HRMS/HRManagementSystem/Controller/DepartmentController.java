package HRMS.HRManagementSystem.Controller;

import HRMS.HRManagementSystem.Entity.Department;
import HRMS.HRManagementSystem.Entity.Role;
import HRMS.HRManagementSystem.Service.DeparmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Department")
public class DepartmentController {
    @Autowired
    DeparmentService deparmentService;
    @PostMapping("/savedepartment")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
         return new ResponseEntity<>(deparmentService.saveDepartment(department), HttpStatus.OK);
    }
    @GetMapping("/getemployee")
    public ResponseEntity<List<Department>> getAllRoles() {
        return new ResponseEntity<>(deparmentService.getAllRoles(),HttpStatus.OK);
    }
    @GetMapping("/getemployeebyid/{id}")
    public ResponseEntity<Department> getDepartmentId(@PathVariable long id) {
        Optional<Department> department= Optional.ofNullable(deparmentService.getDepartmentId(id));
        if (department.isPresent()){
            return new ResponseEntity<>(department.get(),HttpStatus.OK);
        }
        else{
            throw new RuntimeException("Role not found for id: " + id);
        }
    }
    @DeleteMapping("/deletebyid/{id}")
    public String deleteRole(@PathVariable long id) {
       Optional<Department> department = Optional.ofNullable(deparmentService.getDepartmentId(id));
        if(department.isPresent()) {
            deparmentService.deleteDepartment(id);
            return "Deleted Successfully";
        }
        else{
            throw new RuntimeException("Role not found for id: " + id);
        }
    }
    @PostMapping("/update/{id}")
  public ResponseEntity<Department> updateDepartment(@PathVariable long id,@RequestBody Department department){
        try{
            Department department1 = deparmentService.updateDepartment(id,department);
            return new ResponseEntity<>(department,HttpStatus.OK);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
  }

}
