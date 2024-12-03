package com.example.realestate.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
public class User_Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Link with User table

    @ManyToOne
    @JoinColumn(name = "package_id", nullable = false)
    private Package aPackage;

    private LocalDateTime startDate;  // Date sign up service
    private LocalDateTime endDate;    // Date expire
}
