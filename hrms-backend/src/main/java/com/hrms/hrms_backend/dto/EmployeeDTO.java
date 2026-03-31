package com.hrms.hrms_backend.dto;

import jakarta.validation.constraints.*;

public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "First name required")
    private String firstName;

    @NotBlank(message = "Last name required")
    private String lastName;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email required")
    private String email;

    @NotBlank(message = "Department required")
    private String department;

    @NotNull(message = "Salary required")
    @Min(value = 1, message = "Salary must be greater than 0")
    private Double salary;


    // ✅ getters setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}