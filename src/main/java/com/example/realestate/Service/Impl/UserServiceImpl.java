package com.example.realestate.Service.Impl;

import com.example.realestate.Model.User;
import com.example.realestate.Model.User_Package;
import com.example.realestate.Repository.UserPackageRepository;
import com.example.realestate.Repository.UserRepository;
import com.example.realestate.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserPackageRepository UserPackageRepository;
    @Autowired
    private UserPackageRepository userPackageRepository;

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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserByRole(String role) {
        return userRepository.findUserByRole(role);
    }

    @Override
    public Boolean updateUserAccess(Long id, Boolean status){
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User u = user.get();
            u.setIsEnable(status);
            userRepository.save(u);
            return true;
        }
        return false;
    }

    @Override
    public void saveAdmin(User admin) {

        admin.setRole("ROLE_ADMIN");

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        admin.setIsEnable(true);

        admin.setCheckStatus(true);

        userRepository.save(admin);
    }

    @Override
    public void saveUserPackage(User_Package userPackage){
        userPackageRepository.save(userPackage);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

}
