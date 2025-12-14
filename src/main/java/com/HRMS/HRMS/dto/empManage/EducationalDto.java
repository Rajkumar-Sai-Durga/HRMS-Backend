package com.HRMS.HRMS.dto.empManage;

import com.HRMS.HRMS.model.Employees;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationalDto {
    private String degreeType;
    private String degree;
    private String specialization;
    private Date started;
    private Date ended;
    private String cgpa;
}
