package com.HRMS.HRMS.dto.empManage;

import com.HRMS.HRMS.model.Employees;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessionalDetails {
    private String department;
    private String jobTitle;
    private String category;
}
