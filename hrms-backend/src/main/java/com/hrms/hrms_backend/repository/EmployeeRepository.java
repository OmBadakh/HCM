package com.hrms.hrms_backend.repository;

import com.hrms.hrms_backend.entity.Employee;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByIsDeletedFalse(Pageable pageable);
}