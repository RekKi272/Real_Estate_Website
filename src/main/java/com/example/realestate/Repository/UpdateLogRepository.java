package com.example.realestate.Repository;

import com.example.realestate.Model.UpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdateLogRepository extends JpaRepository<UpdateLog, Long> {
    public List<UpdateLog> findByField(UpdateLog.updateField updateField);
    @Query("SELECT u FROM UpdateLog u " +
            "WHERE u.field = com.example.realestate.Model.UpdateLog.updateField.STATUS AND FUNCTION('STR_TO_DATE', u.formattedDate, '%d/%m/%Y') " +
            "BETWEEN FUNCTION('STR_TO_DATE', :startDate, '%d/%m/%Y') AND FUNCTION('STR_TO_DATE', :endDate, '%d/%m/%Y')")
    List<UpdateLog> findByDateBetween(@Param("startDate") String startDate,
                                      @Param("endDate") String endDate);
}
