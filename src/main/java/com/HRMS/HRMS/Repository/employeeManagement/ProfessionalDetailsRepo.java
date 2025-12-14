package com.HRMS.HRMS.Repository.employeeManagement;

import com.HRMS.HRMS.model.employeeManagement.EmployeeManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProfessionalDetailsRepo extends JpaRepository<EmployeeManage, Integer> {
    @Query(value = "SELECT * FROM employee_manage WHERE employee_id = :employee_id", nativeQuery = true)
    EmployeeManage findByEmployeeId(@Param("employee_id") String employeeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employee_manage " +
            "SET department = :department, " +
            "    job_title = :jobTitle, " +
            "    category = :category " +
            "WHERE employee_id = :employeeId",
            nativeQuery = true)
    void updateDetailsByEmployeeId(
            @Param("employeeId") String employeeId,
            @Param("department") String department,
            @Param("jobTitle") String jobTitle,
            @Param("category") String category);

}
