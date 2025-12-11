package com.HRMS.HRMS.services;

import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.model.Employees;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public String generateEmployeeId(){
        long number = System.currentTimeMillis() % 100000; // keeps last 5 digits
        return "EMP" + String.format("%05d", number);
    }

    EmployeeRepo employeeRepo;
    PasswordEncoder passwordEncoder;
    EmployeeService(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder){
        this.employeeRepo=employeeRepo;
        this.passwordEncoder=passwordEncoder;
    }

    public String addEmployee(Employees employee) {
        Employees existedEmp = employeeRepo.findByEmail(employee.getEmail());
        if(existedEmp == null){
            employee.setEmployeeId(generateEmployeeId());
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeRepo.save(employee);
            return "Registration Successful";
        }
        return "Employee with this email already exists";
    }
}
