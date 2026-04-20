package com.hrms.hrms_backend.service;

import com.hrms.hrms_backend.entity.Attendance;
import com.hrms.hrms_backend.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repo;

    // 🔍 Get attendance by range
    public List<Attendance> getAttendance(Long empId, LocalDate start, LocalDate end) {
        return repo.findByEmployeeIdAndDateBetween(empId, start, end);
    }

    // ⏱ Punch In
    public Attendance punchIn(Long empId) {
        LocalDate today = LocalDate.now();

        Attendance existing = repo.findByEmployeeIdAndDate(empId, today);
        if (existing != null) return existing;

        Attendance att = new Attendance();
        att.setEmployeeId(empId);
        att.setDate(today);
        att.setPunchIn(LocalDateTime.now());

        return repo.save(att);
    }

    // ⏱ Punch Out
    public Attendance punchOut(Long empId) {
        LocalDate today = LocalDate.now();

        Attendance att = repo.findByEmployeeIdAndDate(empId, today);
        if (att != null) {
            att.setPunchOut(LocalDateTime.now());
            return repo.save(att);
        }
        return null;
    }
}