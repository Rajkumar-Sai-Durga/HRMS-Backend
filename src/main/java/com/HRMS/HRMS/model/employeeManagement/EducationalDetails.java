package com.HRMS.HRMS.model.employeeManagement;

import com.HRMS.HRMS.model.Employees;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employees employee;

    private String degreeType;
    private String degree;
    private String institute;
    private String specialization;
    private Date started;
    private Date ended;
    private String cgpa;
}
