package com.HRMS.HRMS.Repository.employeeManagement;

import com.HRMS.HRMS.model.employeeManagement.EducationalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalRepo extends JpaRepository<EducationalDetails, Integer> {
    @Query(value = "SELECT * FROM educational_details ed WHERE ed.employee_id = :employeeId", nativeQuery = true)
    List<EducationalDetails> getAllEducationalDetailsByEmployeeId(String employeeId);
}
