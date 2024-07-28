package HRMS.HRManagementSystem.Service;

import HRMS.HRManagementSystem.Entity.User;
import HRMS.HRManagementSystem.Repository.UserRepo;
import HRMS.HRManagementSystem.ServiceImp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceImp {
@Autowired
    UserRepo userRepo;
    @Override
    public User saveUserData(User user) {
       return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }
    @Override
    public User getUserbyId(long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);

        }
    }

    @Override
    public String deleteUser(long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            userRepo.deleteById(id);
            return "Deleted Successfully";
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);

        }
    }

    @Override
    public User updateUser(long id, User user) {
        Optional<User> user1 = userRepo.findById(id);
        if(user1.isPresent()){
            User user2 = user1.get();
            user2.setUsername(user.getUsername());
            user2.setPassword(user.getPassword());
            user2.setRole(user2.getRole());
            return userRepo.save(user2);
        }
        else{
            throw new RuntimeException("Employee not found for id: " + id);

        }
    }
}
