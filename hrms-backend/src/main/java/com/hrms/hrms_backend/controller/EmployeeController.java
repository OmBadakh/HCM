package com.hrms.hrms_backend.controller;

import com.hrms.hrms_backend.dto.ApiResponse;
import com.hrms.hrms_backend.dto.EmployeeDTO;
import com.hrms.hrms_backend.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // ================= GET =================
    @GetMapping
    public ApiResponse<?> getAll(Pageable pageable) {
        return employeeService.getAll(pageable);
    }

    // ================= POST =================
    @PostMapping
    public ApiResponse<?> create(@Valid @RequestBody EmployeeDTO dto) {
        return employeeService.create(dto);
    }
}