package com.HRMS.HRMS.dto.employeeIssues;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeIssueDto {
    private String issue;
    private String description;
    private String category;
    private String priority;
}
