package com.example.realestate.Service.Impl;

import com.example.realestate.Model.Property;
import com.example.realestate.Model.UpdateLog;
import com.example.realestate.Model.User_Package;
import com.example.realestate.Service.ExcelExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {
    private static final double COMMISSION_RATE_RENT = 0.3;
    private static final double COMMISSION_RATE_SALE = 0.5;
    public void exportDataToExcel(HttpServletResponse response, List<UpdateLog> updateLogs) throws IOException {
        // Set the response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=successful_property_transactions.xlsx");

        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Properties");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Date","Property Id", "Price", "Rate", "Service Type", "User Id","User Name", "Phone Number", "Email"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        // Populate data rows
        int rowNum = 1;
        for (UpdateLog updateLog : updateLogs) {
            System.out.println(updateLog.toString() + "hahha");
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(updateLog.getFormattedDate());
            row.createCell(1).setCellValue(updateLog.getProperty().getId());
            row.createCell(2).setCellValue(updateLog.getOldPrice());
            if(updateLog.getEvent().equalsIgnoreCase("Rented")){
                row.createCell(3).setCellValue(COMMISSION_RATE_RENT);
                row.createCell(4).setCellValue("RENT");
            } else {
                row.createCell(3).setCellValue(COMMISSION_RATE_SALE);
                row.createCell(4).setCellValue("SALE");
            }
            row.createCell(5).setCellValue(updateLog.getUser().getId());
            row.createCell(6).setCellValue(updateLog.getUser().getName());
            row.createCell(7).setCellValue(updateLog.getUser().getPhone());
            row.createCell(8).setCellValue(updateLog.getUser().getEmail());
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook to the response
        workbook.write(response.getOutputStream());
        workbook.close();
    }

    public void exportUserPackageDataToExcel(HttpServletResponse response, List<User_Package> userPackages) throws IOException {
        // Set the response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=user_packages.xlsx");

        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("User Packages");

        // Create a header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"User Id", "Package Id", "Price Package" , "Start Date", "End Date", "User Name" , "Phone Number", "Email"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            CellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            cell.setCellStyle(style);
        }

        // Populate data rows
        int rowNum = 1;
        for (User_Package userPackage : userPackages) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(userPackage.getUser().getId());
            row.createCell(1).setCellValue(userPackage.getAPackage().getId());
            row.createCell(2).setCellValue(userPackage.getAPackage().getPrice());
            row.createCell(3).setCellValue(userPackage.getStartDate().toString());
            row.createCell(4).setCellValue(userPackage.getEndDate().toString());
            row.createCell(5).setCellValue(userPackage.getUser().getName());
            row.createCell(6).setCellValue(userPackage.getUser().getPhone());
            row.createCell(7).setCellValue(userPackage.getUser().getEmail());
        }

        // Auto-size columns
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the workbook to the response
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
