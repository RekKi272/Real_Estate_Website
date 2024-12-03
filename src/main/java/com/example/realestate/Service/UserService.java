package com.example.realestate.Service;

import com.example.realestate.Model.User;
import com.example.realestate.Model.User_Package;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void saveUser(User user);
    boolean isEmailExist(String email);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getUserByRole(String role);
    Boolean updateUserAccess(Long id, Boolean status);
    void saveAdmin(User user);
    void saveUserPackage(User_Package userPackage);
    void updateUser(User user);
}
