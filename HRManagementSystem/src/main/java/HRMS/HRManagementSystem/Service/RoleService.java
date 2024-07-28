package HRMS.HRManagementSystem.Service;

import HRMS.HRManagementSystem.Entity.Role;
import HRMS.HRManagementSystem.Repository.RoleRepo;
import HRMS.HRManagementSystem.ServiceImp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService implements RoleServiceImp {
    @Autowired
    RoleRepo roleRepo;
    @Override
    public Role saveRoleData(Role role){
        return roleRepo.save(role);
    }
    @Override
    public List<Role> getAllRoles(){
        return roleRepo.findAll();
    }
    @Override
    public Role getRolebyId(long id){

        return roleRepo.findById(id).get();
    }
    @Override
    public String deleteRole(long id){
        roleRepo.deleteById(id);
        return "Deleted Role Succesfully";
    }

    @Override
    public Role updateRole(long id, Role roleDetails) {
        Optional<Role> optionalRole = roleRepo.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setRole(roleDetails.getRole());
            return roleRepo.save(role);
        } else {
            throw new RuntimeException("Role not found for id: " + id);
        }
    }


}
