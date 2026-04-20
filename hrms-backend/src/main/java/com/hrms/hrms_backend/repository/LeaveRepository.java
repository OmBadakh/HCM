package com.hrms.hrms_backend.repository;

import com.hrms.hrms_backend.entity.LeaveManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;   // ✅ IMPORTANT

@Repository
public interface LeaveRepository extends JpaRepository<LeaveManagement, Long> {

    List<LeaveManagement> findByEmployeeId(Long employeeId);
    @Query("SELECT l.leaveType, SUM(l.totalDays) FROM LeaveManagement l WHERE l.employeeId = :empId AND MONTH(l.startDate) = :month AND YEAR(l.startDate) = :year GROUP BY l.leaveType")
    List<Object[]> getLeaveSummary(Long empId, int month, int year);
}