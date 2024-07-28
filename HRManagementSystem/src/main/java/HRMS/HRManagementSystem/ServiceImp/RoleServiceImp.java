package HRMS.HRManagementSystem.ServiceImp;

import HRMS.HRManagementSystem.Entity.Role;
import java.util.*;
public interface RoleServiceImp {
    public Role saveRoleData(Role role);
    public List<Role> getAllRoles();
    public Role getRolebyId(long id);
    public String deleteRole(long id);
    public Role updateRole(long id,Role role);
}
