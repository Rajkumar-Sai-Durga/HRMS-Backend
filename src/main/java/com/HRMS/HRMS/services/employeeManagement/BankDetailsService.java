package com.HRMS.HRMS.services.employeeManagement;

import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.Repository.employeeManagement.BankDetailsRepo;
import com.HRMS.HRMS.dto.empManage.BankDetailsDto;
import com.HRMS.HRMS.model.Employees;
import com.HRMS.HRMS.model.employeeManagement.BankDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankDetailsService {
    BankDetailsRepo bankDetailsRepo;
    EmployeeRepo employeeRepo;
    BankDetailsService(BankDetailsRepo bankDetailsRepo, EmployeeRepo employeeRepo){
        this.bankDetailsRepo=bankDetailsRepo;
        this.employeeRepo=employeeRepo;
    }

    public boolean postBankDetails(String employeeId, BankDetailsDto dto) {
        Employees emp = employeeRepo.findEmployeeByEmployeeId(employeeId);
        try {
            BankDetails bd = new BankDetails();
            bd.setEmployee(emp);
            bd.setBankName(dto.getBankName());
            bd.setAccountNumber(dto.getAccountNumber());
            bd.setIfscCode(dto.getIfscCode());
            bankDetailsRepo.save(bd);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<BankDetails> getAllBankDetails(String employeeId) {
        try {
            return bankDetailsRepo.getAllBankDetailsById(employeeId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<BankDetails> deleteBankDetailsById(int bankId, String employeeId) {
        try {
            bankDetailsRepo.deleteById(bankId);
            return getAllBankDetails(employeeId);
        } catch (Exception e) {
            return null;
        }
    }
}
