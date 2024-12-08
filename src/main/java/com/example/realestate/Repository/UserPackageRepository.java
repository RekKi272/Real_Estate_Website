package com.example.realestate.Repository;

import com.example.realestate.Model.User_Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserPackageRepository extends JpaRepository<User_Package, Long> {
    List<User_Package> findAllByEndDateBefore(LocalDateTime localDateTime);
    List<User_Package> findByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
