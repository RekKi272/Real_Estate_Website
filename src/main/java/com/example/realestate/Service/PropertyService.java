package com.example.realestate.Service;

import com.example.realestate.Model.Property;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PropertyService {
    List<Property> listPropertyByIsPublic(Boolean isPublic);
}
