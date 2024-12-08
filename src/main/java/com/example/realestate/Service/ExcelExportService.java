package com.example.realestate.Service;

import com.example.realestate.Model.Property;
import com.example.realestate.Model.UpdateLog;
import com.example.realestate.Model.User_Package;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface ExcelExportService {
    void exportDataToExcel(HttpServletResponse response, List<UpdateLog> updateLogs) throws IOException;
    void exportUserPackageDataToExcel(HttpServletResponse response, List<User_Package> userPackages) throws IOException;
}
