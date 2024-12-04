package com.example.realestate.Service.Impl;

import com.example.realestate.Repository.PackageRepository;
import com.example.realestate.Service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.realestate.Model.Package;

import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageRepository packageRepository;

    @Override
    public List<Package> getAllPackages() {
        return packageRepository.findAll();
    }

    @Override
    public Package getPackageById(Long id){
        return packageRepository.findById(id).get();
    }

    @Override
    public void save(Package pack){
        packageRepository.save(pack);
    }
}
