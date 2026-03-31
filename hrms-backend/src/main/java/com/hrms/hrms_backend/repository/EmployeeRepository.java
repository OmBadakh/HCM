package com.hrms.hrms_backend.repository;

import com.hrms.hrms_backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Page<Employee> findByFirstNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Employee> findByFirstNameContainingIgnoreCaseAndIsDeletedFalse(
            String name,
            Pageable pageable
    );

    Page<Employee> findByFirstNameContainingIgnoreCaseAndDepartmentContainingIgnoreCase(
            String name,
            String department,
            Pageable pageable
    );
    Page<Employee>
    findByFirstNameContainingIgnoreCaseAndDepartmentContainingIgnoreCaseAndIsDeletedFalse(
            String name,
            String department,
            Pageable pageable
    );

    Optional<Employee> findByIdAndIsDeletedFalse(Long id);
    Page<Employee> findByIsDeletedFalse(Pageable pageable);


}