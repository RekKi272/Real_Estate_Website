package com.example.realestate.Service.Impl;

import com.example.realestate.Model.User_Package;
import com.example.realestate.Repository.UserPackageRepository;
import com.example.realestate.Service.UserPackageService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserPackageServiceImpl implements UserPackageService {
    @Autowired
    private UserPackageRepository userPackageRepository;
    public List<User_Package> getUserPackagesBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return userPackageRepository.findByStartDateBetween(startDate, endDate);
    }
}
