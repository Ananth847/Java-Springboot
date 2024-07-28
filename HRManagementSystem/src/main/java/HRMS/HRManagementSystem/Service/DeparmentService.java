package HRMS.HRManagementSystem.Service;

import HRMS.HRManagementSystem.Entity.Department;
import HRMS.HRManagementSystem.Entity.Role;
import HRMS.HRManagementSystem.Repository.DepartmentRepo;
import HRMS.HRManagementSystem.ServiceImp.DepartmentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeparmentService implements DepartmentServiceImp {
    @Autowired
    DepartmentRepo departmentRepo;
    public Department saveDepartment(Department department){

        return departmentRepo.save(department);
    }
    @Override
    public List<Department> getAllRoles() {

        return departmentRepo.findAll();
    }
    @Override
    public Department getDepartmentId(long id) {

        return departmentRepo.findById(id).get();
    }
    @Override
    public String deleteDepartment(long id) {
        departmentRepo.deleteById(id);
        return "Deleted record Successfully";
    }

    @Override
    public Department updateDepartment(long id, Department department) {
        Optional<Department> dep = departmentRepo.findById(id);
        if(dep.isPresent()){
            Department department1 = dep.get();
            department1.setDeptname(department.getDeptname());
            return departmentRepo.save(department1);
        }
       else{
           throw new RuntimeException("Role not found for id: " + id);
        }
    }


}
