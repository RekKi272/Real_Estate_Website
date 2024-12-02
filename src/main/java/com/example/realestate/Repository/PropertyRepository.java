package com.example.realestate.Repository;

import com.example.realestate.Model.Property;
import com.example.realestate.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findPropertiesByIsPublic(Boolean isPublic);
    List<Property> findPropertyByPostedBy(User user);

    @Query("SELECT p FROM Property p WHERE p.isPublic = true ORDER BY p.createdAt DESC")
    List<Property> findPublicPropertiesOrderedByCreatedAt();
}
