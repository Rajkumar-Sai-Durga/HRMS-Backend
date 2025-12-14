package com.HRMS.HRMS.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeIssues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employees employee;

    private String issue;
    private String description;
    private String category;
    private String priority;
    private Date appliedAt;

    private String Status;
    @Column(name = "resolved_by_id")
    private String resolvedById;
    @Column(name = "resolved_by_name")
    private String resolvedByName;
    @Column(name = "resolved_at")
    private Date resolvedAt;
}
