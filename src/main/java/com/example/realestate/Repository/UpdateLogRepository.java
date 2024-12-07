package com.example.realestate.Repository;

import com.example.realestate.Model.UpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateLogRepository extends JpaRepository<UpdateLog, Long> {
}
