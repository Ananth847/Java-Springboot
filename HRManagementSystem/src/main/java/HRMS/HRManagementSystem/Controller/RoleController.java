package HRMS.HRManagementSystem.Controller;
import HRMS.HRManagementSystem.Entity.Role;
import HRMS.HRManagementSystem.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("Role")
public class RoleController {
    @Autowired
    RoleService roleService;
    @PostMapping("/saveData")
    public ResponseEntity<Role> saveRoleData(@RequestBody Role role){
        return new ResponseEntity<>(roleService.saveRoleData(role), HttpStatus.OK);
    }
    @GetMapping("/getallroles")
    public ResponseEntity<List<Role>> getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(),HttpStatus.OK);
    }
    @GetMapping("/getelementbyid/{id}")
    public ResponseEntity<Role> getRolebyId(@PathVariable long id){
       Optional<Role> role = Optional.ofNullable(roleService.getRolebyId(id));
             if(role.isPresent()){
                 return new ResponseEntity<>(role.get(),HttpStatus.OK);
             }
        else{
                 throw new RuntimeException("Role not found for id: " + id);
             }
    }
    @DeleteMapping("/deletebyid/{id}")
    public String deleteRole(@PathVariable long id) {
        Optional<Role> role = Optional.ofNullable(roleService.getRolebyId(id));
        if (role.isPresent()) {
            roleService.deleteRole(id);
            return "Deleted Role Successfully";
        } else {
            throw new RuntimeException("Role not found for id: " + id);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity <Role> updateRole(@PathVariable long id, @RequestBody Role roleDetails) {
        try {
            Role updatedRole = roleService.updateRole(id, roleDetails);
            return new ResponseEntity<>(updatedRole,HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
