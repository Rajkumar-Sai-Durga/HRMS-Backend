package com.HRMS.HRMS.Repository;

import com.HRMS.HRMS.model.EmployeeIssues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface EmployeeIssuesRepo extends JpaRepository<EmployeeIssues, Integer> {

    @Query(value = "SELECT * FROM employee_issues WHERE employee_id = :employeeId", nativeQuery = true)
    List<EmployeeIssues> findAllByEmployeeId(@Param("employeeId") String employeeId);

    @Modifying
    @Transactional
    @Query(
            value = """
            UPDATE employee_issues
            SET
                status = :status,
                resolved_by_id = :employeeId,
                resolved_by_name = :firstName,
                resolved_at = :date
            WHERE id = :issueId
            """,
            nativeQuery = true
    )
    void updateByAdmin(@Param("issueId") int issueId,
                      @Param("status") String status,
                      @Param("employeeId") String employeeId,
                      @Param("firstName") String firstName,
                      @Param("date") Date date);
}
