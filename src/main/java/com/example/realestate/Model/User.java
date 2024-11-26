package com.example.realestate.Model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String phone;

    private Boolean checkStatus; // Kiem tra nguoi dung da dang ky dich vu chua

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Boolean isEnable;
}
