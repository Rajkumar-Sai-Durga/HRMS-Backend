package com.HRMS.HRMS.Repository;

import com.HRMS.HRMS.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employees, Integer> {

    Optional<Employees> findByEmail(String email);

    public Employees findEmployeeByEmployeeId(String employeeId);

    @Query(value = "SELECT * FROM employees WHERE email = :email", nativeQuery = true)
    Employees findEmployeeByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE employees SET password = :password WHERE email = :email", nativeQuery = true)
    void updatePasswordByEmail(@Param("email") String email, @Param("password") String password);
}
