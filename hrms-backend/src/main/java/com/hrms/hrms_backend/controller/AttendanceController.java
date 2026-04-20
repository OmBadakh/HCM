package com.hrms.hrms_backend.controller;

import com.hrms.hrms_backend.entity.Attendance;
import com.hrms.hrms_backend.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    // 🔍 Search attendance
    @GetMapping
    public List<Attendance> getAttendance(
            @RequestParam Long employeeId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return service.getAttendance(
                employeeId,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
        );
    }

    // ⏱ Punch In
    @PostMapping("/punch-in")
    public Attendance punchIn(@RequestParam Long employeeId) {
        return service.punchIn(employeeId);
    }

    // ⏱ Punch Out
    @PostMapping("/punch-out")
    public Attendance punchOut(@RequestParam Long employeeId) {
        return service.punchOut(employeeId);
    }
}