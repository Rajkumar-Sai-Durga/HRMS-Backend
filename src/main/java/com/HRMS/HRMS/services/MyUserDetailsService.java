package com.HRMS.HRMS.services;

import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.model.Employees;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final EmployeeRepo employeeRepo;
    MyUserDetailsService (EmployeeRepo employeeRepo){
        this.employeeRepo=employeeRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {


        Employees employee = employeeRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not Found"));

        return User.withUsername(employee.getEmail())
                .password(employee.getPassword())
                .roles(employee.getRole()) // EMPLOYEE / HR
                .build();
    }

}

