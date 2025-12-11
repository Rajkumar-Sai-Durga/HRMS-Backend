package com.HRMS.HRMS.Repository;

import com.HRMS.HRMS.model.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeavesRepo extends JpaRepository<Leaves, Integer> {
    @Query(value = "SELECT * FROM leaves WHERE employee_id = :employeeId", nativeQuery = true)
    public List<Leaves> findLeaveApplicationsByEmployeeId(@Param("employeeId") String employeeId);
}
