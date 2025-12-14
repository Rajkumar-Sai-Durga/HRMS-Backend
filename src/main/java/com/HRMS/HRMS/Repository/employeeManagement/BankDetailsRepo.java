package com.HRMS.HRMS.Repository.employeeManagement;

import com.HRMS.HRMS.model.employeeManagement.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface BankDetailsRepo extends JpaRepository<BankDetails, Integer> {
    @Query(value = "SELECT * FROM bank_details WHERE employee_id = :employeeId", nativeQuery = true)
    List<BankDetails> getAllBankDetailsById(String employeeId);
}
