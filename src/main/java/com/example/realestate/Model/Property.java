package com.example.realestate.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String address;

    private String description;

    private String propertyType; // Flat, Building, etc

    @Column(nullable = false)
    private String serviceType; // for sale, rent, etc

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String Country;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer bedrooms;

    @Column(nullable = false)
    private Integer bathrooms;

    @Column(nullable = false)
    private Integer floors;

    @Column(nullable = false)
    private Integer balcony;

    @Column(nullable = false)
    private Double size;

    @Column(nullable = false)
    private String status; // ready to move, building, fixing, etc...

    @Column(nullable = false)
    private Boolean isPublic; // must be verified by admin before public

    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User postedBy;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

}
