package com.hrms.hrms_backend.controller;

import com.hrms.hrms_backend.dto.DashboardResponse;
import com.hrms.hrms_backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping
    public DashboardResponse getDashboard(
            @RequestParam Long employeeId,
            @RequestParam int month,
            @RequestParam int year) {

        return service.getDashboard(employeeId, month, year);
    }
}