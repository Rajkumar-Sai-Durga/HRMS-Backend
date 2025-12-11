package com.HRMS.HRMS.Repository;

import com.HRMS.HRMS.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employees, Integer> {
    public Employees findByEmail(String email);
}
