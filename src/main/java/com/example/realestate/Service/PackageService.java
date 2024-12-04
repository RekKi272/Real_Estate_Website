package com.example.realestate.Service;

import org.springframework.stereotype.Service;
import com.example.realestate.Model.Package;
import java.util.List;

@Service
public interface PackageService {
    List<Package> getAllPackages();
    Package getPackageById(Long id);
    void save(Package pack);
}
