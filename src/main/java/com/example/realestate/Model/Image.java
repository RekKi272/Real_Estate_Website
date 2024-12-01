package com.example.realestate.Model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @ToString.Exclude
    private Property property; // Many images reference for one property

    @Override
    public String toString() {
        return "Image{id=" + id + ", url='" + url + "'}";
    }
}
