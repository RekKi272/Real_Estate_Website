package com.example.realestate.Service.Impl;

import com.example.realestate.Model.Property;
import com.example.realestate.Repository.PropertyRepository;
import com.example.realestate.Service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Override
    public List<Property> listPropertyByIsPublic(Boolean isPublic){
        return propertyRepository.findPropertiesByIsPublic(isPublic);
    }

}
