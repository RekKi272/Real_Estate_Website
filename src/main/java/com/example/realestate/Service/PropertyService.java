package com.example.realestate.Service;

import com.example.realestate.Model.Image;
import com.example.realestate.Model.Property;
import com.example.realestate.Model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public interface PropertyService {
    List<Property> listPropertyByIsPublic(Boolean isPublic);

    void saveProperty(Property property, MultipartFile[] images) throws IOException;

    void save(Property property);

    void delete(Property property);

    List<Property> getListPropertyByUser(User user);

    List<Property> getAllProperty();
    Property getPropertyById(Long id);

    List<Image> saveImages(Property property, MultipartFile[] images) throws IOException;

    List<Property> latestPublicProperty();

    List<Property> getFilteredProperties(String category, String serviceType, String status);

    List<Property> getSearchedProperties(String cityName, String serviceType, Integer bedrooms);

    List<Property> getSearchRequest(String city, String serviceType, String propertyType, Integer bedrooms, Double minimumPrice, Double maximumPrice, String status);
}
