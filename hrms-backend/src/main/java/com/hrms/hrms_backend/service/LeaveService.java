package com.hrms.hrms_backend.service;

import com.hrms.hrms_backend.entity.LeaveManagement;
import com.hrms.hrms_backend.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;          // ✅ IMPORTANT
import java.time.LocalDate;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository repo;

    public LeaveManagement applyLeave(LeaveManagement leave) {

        int days = (int) (leave.getEndDate().toEpochDay() - leave.getStartDate().toEpochDay()) + 1;
        leave.setTotalDays(days);

        return repo.save(leave);
    }

    public List<LeaveManagement> getLeaves(Long empId) {
        return repo.findByEmployeeId(empId);
    }
}