package com.hrms.hrms_backend.service;

import com.hrms.hrms_backend.dto.ApiResponse;
import com.hrms.hrms_backend.dto.EmployeeDTO;
import com.hrms.hrms_backend.entity.Employee;
import com.hrms.hrms_backend.repository.EmployeeRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // ================= GET =================
    public ApiResponse<?> getAll(Pageable pageable) {

        Page<Employee> page =
                employeeRepository.findByIsDeletedFalse(pageable);

        return new ApiResponse<>("Employees fetched", page);
    }

    // ================= CREATE =================
    public ApiResponse<?> create(EmployeeDTO dto) {

        Employee emp = new Employee();

        emp.setFirstName(dto.getFirstName());
        emp.setLastName(dto.getLastName());
        emp.setEmail(dto.getEmail());
        emp.setDepartment(dto.getDepartment());
        emp.setSalary(dto.getSalary());
        emp.setIsDeleted(false);

        employeeRepository.save(emp);

        return new ApiResponse<>("Employee added successfully", emp);
    }
}