package com.HRMS.HRMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private String reason;
}
