package HRMS.HRManagementSystem.ServiceImp;

import HRMS.HRManagementSystem.Entity.Role;
import HRMS.HRManagementSystem.Entity.User;

import java.util.List;

public interface UserServiceImp {
    public User saveUserData(User user);
    public List<User> getAllUser();
    public User getUserbyId(long id);
    public String deleteUser(long id);
    public User updateUser(long id,User user);
}
