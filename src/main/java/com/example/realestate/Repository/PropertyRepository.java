package com.example.realestate.Repository;

import com.example.realestate.Model.Property;
import com.example.realestate.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findPropertiesByIsPublic(Boolean isPublic);
    List<Property> findPropertyByPostedBy(User user);

    @Query("SELECT p FROM Property p WHERE p.isPublic = true ORDER BY p.createdAt DESC")
    List<Property> findPublicPropertiesOrderedByCreatedAt();

    List<Property> findByPropertyTypeIgnoreCaseAndServiceTypeIgnoreCaseAndIsPublicTrue(String propertyType, String serviceType);

    List<Property> findByStatusIgnoreCaseAndIsPublicTrue(String status);

    List<Property> findByCityIgnoreCaseAndPropertyTypeIgnoreCaseAndBedroomsAndIsPublicTrue(String city, String propertyType, Integer bedrooms);

    @Query("SELECT p FROM Property p WHERE " +
            "(:city IS NULL OR LOWER(p.city) LIKE LOWER(CONCAT('%', :city, '%'))) AND " +
            "(:serviceType IS NULL OR LOWER(p.serviceType) = LOWER(:serviceType)) AND " +
            "(:propertyType IS NULL OR LOWER(p.propertyType) = LOWER(:propertyType)) AND " +
            "(:bedrooms IS NULL OR p.bedrooms = :bedrooms) AND " +
            "(:minimumPrice IS NULL OR p.price >= :minimumPrice) AND " +
            "(:maximumPrice IS NULL OR p.price <= :maximumPrice) AND " +
            "(:status IS NULL OR LOWER(p.status) = LOWER(:status))")
    List<Property> findPropertiesByFilters(
            @Param("city") String city,
            @Param("serviceType") String serviceType,
            @Param("propertyType") String propertyType,
            @Param("bedrooms") Integer bedrooms,
            @Param("minimumPrice") Double minimumPrice,
            @Param("maximumPrice") Double maximumPrice,
            @Param("status") String status
    );
}
