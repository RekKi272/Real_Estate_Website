package com.example.realestate.Service;

import com.example.realestate.Model.UpdateLog;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface UpdateLogService {
    void saveUpdateLog(UpdateLog updateLog);
    List<UpdateLog> getUpdateLogStatus();
    List<UpdateLog> getUpdateLogInPeriod(LocalDate startDate, LocalDate endDate);
}
