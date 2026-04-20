package com.hrms.hrms_backend.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "attendance",
        uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "date"}))
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    private LocalDate date;

    private LocalDateTime punchIn;

    private LocalDateTime punchOut;

    // Getters & Setters
    public Long getEmployeeId() { return employeeId; }
    public void setEmployeeId(Long employeeId) { this.employeeId = employeeId; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalDateTime getPunchIn() { return punchIn; }
    public void setPunchIn(LocalDateTime punchIn) { this.punchIn = punchIn; }

    public LocalDateTime getPunchOut() { return punchOut; }
    public void setPunchOut(LocalDateTime punchOut) { this.punchOut = punchOut; }
}