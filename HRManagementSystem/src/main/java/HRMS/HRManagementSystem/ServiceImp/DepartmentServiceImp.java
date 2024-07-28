package HRMS.HRManagementSystem.ServiceImp;

import HRMS.HRManagementSystem.Entity.Department;
import HRMS.HRManagementSystem.Entity.Role;

import java.util.List;

public interface DepartmentServiceImp {
    public Department saveDepartment(Department department);
    public List<Department> getAllRoles();
    public Department getDepartmentId(long id);
    public String deleteDepartment(long id);
    public Department updateDepartment(long id,Department department);
}
