package com.example.realestate.Repository;

import com.example.realestate.Model.Property;
import com.example.realestate.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findPropertiesByIsPublic(Boolean isPublic);
    List<Property> findPropertyByPostedBy(User user);
}
