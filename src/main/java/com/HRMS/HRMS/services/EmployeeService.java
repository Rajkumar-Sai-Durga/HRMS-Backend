package com.HRMS.HRMS.services;

import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.model.Employees;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    JavaMailSender javaMailSender;
    EmployeeService(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender){
        this.employeeRepo=employeeRepo;
        this.passwordEncoder=passwordEncoder;
        this.javaMailSender=javaMailSender;
    }

    public String addEmployee(Employees employee) {
        Employees existedEmp = employeeRepo.findEmployeeByEmail(employee.getEmail());
        if(existedEmp == null){
            employee.setEmployeeId(generateEmployeeId());
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeRepo.save(employee);
            return "Registration Successful";
        }
        return "Employee with this email already exists";
    }

    public Employees findEmployeeByEmail(String email){
        return employeeRepo.findEmployeeByEmail(email);
    }


}
