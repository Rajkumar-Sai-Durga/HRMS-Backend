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
public class Leaves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employees employee;

    private String username;

    @Column(name = "leave_type")
    private String leaveType;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "leave_reason")
    private String reason;

    private String status;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_by_name")
    private String approvedByName;

    @Column(name = "applied_at")
    private Date appliedAt;

    @Column(name = "decision_at")
    private Date decisionAt;
}
