package com.example.realestate.Service;

import com.example.realestate.Model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(User user);
    boolean isEmailExist(String email);
    User getUserByEmail(String email);
}
