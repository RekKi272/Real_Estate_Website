package com.example.realestate.Service;

import com.example.realestate.Model.User_Package;

import java.time.LocalDateTime;
import java.util.List;

public interface UserPackageService {
    List<User_Package> getUserPackagesBetween(LocalDateTime startDate, LocalDateTime endDate);
}
