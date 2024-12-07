package com.example.realestate.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String formattedDate;
    @ManyToOne
    @JoinColumn(name = "propertyId", nullable = false)
    private Property property;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
    private updateField field;
    private Double oldPrice;
    private String event;
    public enum updateField {
        PRICE,
        SERVICE_TYPE,
        STATUS
    }

}
