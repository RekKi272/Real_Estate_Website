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
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url; // Image Url or path

    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property; // Many images reference for one property
}
