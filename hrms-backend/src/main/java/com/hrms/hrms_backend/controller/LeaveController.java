package com.hrms.hrms_backend.controller;

import com.hrms.hrms_backend.entity.LeaveManagement;
import com.hrms.hrms_backend.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;   // ✅ IMPORTANT

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin
public class LeaveController {

    @Autowired
    private LeaveService service;

    @PostMapping
    public LeaveManagement applyLeave(@RequestBody LeaveManagement leave) {
        return service.applyLeave(leave);
    }

    @GetMapping
    public List<LeaveManagement> getLeaves(@RequestParam Long employeeId) {
        return service.getLeaves(employeeId);
    }
}