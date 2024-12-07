package com.example.realestate.Service.Impl;

import com.example.realestate.Model.UpdateLog;
import com.example.realestate.Repository.UpdateLogRepository;
import com.example.realestate.Service.UpdateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class UpdateLogServiceImpl implements UpdateLogService {
    @Autowired
    private UpdateLogRepository updateLogRepository;

    public void saveUpdateLog(UpdateLog updateLog) {
        updateLog.setFormattedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        updateLogRepository.save(updateLog);
    }
}
