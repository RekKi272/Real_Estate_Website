package com.example.realestate.Service.Impl;

import com.example.realestate.Model.UpdateLog;
import com.example.realestate.Repository.UpdateLogRepository;
import com.example.realestate.Service.UpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.realestate.Model.UpdateLog.updateField.STATUS;

@Service
public class UpdateLogServiceImpl implements UpdateLogService {
    @Autowired
    private UpdateLogRepository updateLogRepository;

    public void saveUpdateLog(UpdateLog updateLog) {
        updateLog.setFormattedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        updateLogRepository.save(updateLog);
    }

    public List<UpdateLog> getUpdateLogStatus() {
        return updateLogRepository.findByField(UpdateLog.updateField.STATUS);
    }

    public List<UpdateLog> getUpdateLogInPeriod(LocalDate startDate, LocalDate endDate) {
        String formattedStartDate = startDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String formattedEndDate = endDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return updateLogRepository.findByDateBetween(formattedStartDate, formattedEndDate);
    }
}
