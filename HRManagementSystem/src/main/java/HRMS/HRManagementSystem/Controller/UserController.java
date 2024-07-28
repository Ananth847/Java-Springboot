package HRMS.HRManagementSystem.Controller;

import HRMS.HRManagementSystem.Entity.User;
import HRMS.HRManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("User")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/saveuser")
    public ResponseEntity<User> saveRoleData(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUserData(user), HttpStatus.OK);
    }

    @GetMapping("/getallusers")
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("getid/{id}")
    public ResponseEntity<User> getRolebyId(@PathVariable long id) {
        try {
            return new ResponseEntity<>(userService.getUserbyId(id), HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException("Employee not found for id: " + id);
        }
    }

    @DeleteMapping("delete/{id}")
    public String deleteRole(@PathVariable long id) {
        userService.deleteUser(id);
        return "deleted Succesfully";
    }

    @PostMapping("update/{id}")
    public ResponseEntity<User> updateRole(@PathVariable long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }
}
