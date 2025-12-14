package com.HRMS.HRMS.dto.empManage;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankDetailsDto {
    private String bankName;
    private String accountNumber;
    private String ifscCode;
}
