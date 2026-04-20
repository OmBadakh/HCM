package com.hrms.hrms_backend.service;

import com.hrms.hrms_backend.dto.DashboardResponse;
import com.hrms.hrms_backend.repository.AttendanceRepository;
import com.hrms.hrms_backend.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

@Service
public class DashboardService {

    @Autowired
    private AttendanceRepository attendanceRepo;

    @Autowired
    private LeaveRepository leaveRepo;

    public DashboardResponse getDashboard(Long empId, int month, int year) {

        DashboardResponse res = new DashboardResponse();

        // ✅ Present Days
        long present = attendanceRepo.countPresentDays(empId, month, year);
        res.presentDays = present;

        // ✅ Total Days in Month
        int totalDays = YearMonth.of(year, month).lengthOfMonth();

        // ✅ Absent = Total - Present
        res.absentDays = totalDays - present;

        // ✅ Leave Summary
        List<Object[]> leaveData = leaveRepo.getLeaveSummary(empId, month, year);

        for (Object[] row : leaveData) {
            String type = (String) row[0];
            Long count = (Long) row[1];

            switch (type) {
                case "PL": res.plCount = count; break;
                case "CL": res.clCount = count; break;
                case "LWP": res.lwpCount = count; break;
                case "OD": res.odCount = count; break;
            }
        }

        return res;
    }
}