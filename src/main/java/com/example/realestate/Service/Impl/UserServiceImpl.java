package com.example.realestate.Service.Impl;

import com.example.realestate.Model.User;
import com.example.realestate.Repository.UserRepository;
import com.example.realestate.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) {
        // hasn't bought services yet
        user.setCheckStatus(false);

        // set account as active
        user.setIsEnable(true);

        // set default role as ROLE_USER
        user.setRole("ROLE_USER");

        // Encoding the user password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public boolean isEmailExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
