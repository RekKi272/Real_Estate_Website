package com.example.realestate.Service.Impl;

import com.example.realestate.Model.Image;
import com.example.realestate.Model.Property;
import com.example.realestate.Model.UpdateLog;
import com.example.realestate.Model.User;
import com.example.realestate.Repository.ImageRepository;
import com.example.realestate.Repository.PropertyRepository;
import com.example.realestate.Repository.UpdateLogRepository;
import com.example.realestate.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UpdateLogRepository updateLogRepository;

    @Override
    public List<Property> listPropertyByIsPublic(Boolean isPublic){
        return propertyRepository.findPropertiesByIsPublic(isPublic);
    }

    @Override
    public void saveProperty(Property property, MultipartFile[] images) throws IOException {

        propertyRepository.save(property);
        updateLogRepository.saveAll(property.getUpdateLogs());

        List<Image> imageList = new ArrayList<Image>();

        // Save images to database or file system
        for (MultipartFile file : images) {
            if(!file.isEmpty()){
                Image image = new Image();
                image.setProperty(property); // Link to property
                image.setUrl(file.getOriginalFilename());
                imageList.add(image);
            }
        }

        property.setImages(imageList);
        imageRepository.saveAll(imageList);
    }

    @Override
    public void delete(Property property) {
        propertyRepository.delete(property);
    }

    @Override
    public List<Property> getListPropertyByUser(User user){
        return propertyRepository.findPropertyByPostedBy(user);
    }

    @Override
    public List<Property> getAllProperty(){
        return propertyRepository.findAll();
    }

    @Override
    public Property getPropertyById(Long id){
        return propertyRepository.findById(id).get();
    }

    @Override
    public void save(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public List<Image> saveImages(Property property, MultipartFile[] images) throws IOException {
        List<Image> imageList = new ArrayList<>();
        for (MultipartFile file : images) {
            if (!file.isEmpty()) {
                Image image = new Image();
                image.setProperty(property);
                image.setUrl(file.getOriginalFilename());
                imageList.add(image);
            }
        }
        imageRepository.saveAll(imageList);
        return imageList;
    }

    @Override
    public List<Property> latestPublicProperty(){
        return propertyRepository.findPublicPropertiesOrderedByCreatedAt();
    }

    @Override
    public List<Property> getFilteredProperties(String category,
                                                String serviceType,
                                                String status){
        if(category != null && serviceType != null){
            return propertyRepository.findByPropertyTypeIgnoreCaseAndServiceTypeIgnoreCaseAndIsPublicTrue(category, serviceType);
        } else{
            return propertyRepository.findByStatusIgnoreCaseAndIsPublicTrue(status);
        }
    }

    @Override
    public  List<Property> getSearchedProperties(String cityName,
                                                 String propertyType,
                                                 Integer bedrooms){
        if(cityName != null && propertyType != null && bedrooms != null){
            return propertyRepository.findByCityIgnoreCaseAndPropertyTypeIgnoreCaseAndBedroomsAndIsPublicTrue(cityName, propertyType, bedrooms);
        }
        return null;
    }

    @Override
    public List<Property> getSearchRequest(String city,
                                           String serviceType,
                                           String propertyType,
                                           Integer bedrooms,
                                           Double minimumPrice,
                                           Double maximumPrice,
                                           String status){
        return propertyRepository.findPropertiesByFilters(city, serviceType, propertyType, bedrooms, minimumPrice, maximumPrice, status);
    }
}
