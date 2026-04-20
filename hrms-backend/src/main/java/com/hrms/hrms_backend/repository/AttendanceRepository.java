package com.hrms.hrms_backend.repository;

import com.hrms.hrms_backend.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByEmployeeIdAndDateBetween(
            Long employeeId,
            LocalDate startDate,
            LocalDate endDate
    );

    Attendance findByEmployeeIdAndDate(Long employeeId, LocalDate date);
    @Query("SELECT COUNT(a) FROM Attendance a WHERE a.employeeId = :empId AND MONTH(a.date) = :month AND YEAR(a.date) = :year")
    long countPresentDays(Long empId, int month, int year);
}
