package com.HRMS.HRMS.Repository;

import com.HRMS.HRMS.model.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface LeavesRepo extends JpaRepository<Leaves, Integer> {
    @Query(value = "SELECT * FROM leaves WHERE employee_id = :employeeId", nativeQuery = true)
    public List<Leaves> findLeaveApplicationsByEmployeeId(@Param("employeeId") String employeeId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Leaves SET leave_type = :leaveType, start_date = :startDate, end_date = :endDate, leave_reason = :reason WHERE id = :leaveId", nativeQuery = true)
    void updateLeaveDetails(@Param("leaveId") Integer leaveId,
                           @Param("leaveType") String leaveType,
                           @Param("startDate") Date startDate,
                           @Param("endDate") Date endDate,
                           @Param("reason") String reason);
}
