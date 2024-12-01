package com.example.realestate.Service;

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

    List<Property> getListPropertyByUser(User user);
}
