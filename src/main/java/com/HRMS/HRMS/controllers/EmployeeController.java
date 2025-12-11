package com.HRMS.HRMS.controllers;

import com.HRMS.HRMS.dto.LoginRequest;
import com.HRMS.HRMS.dto.LoginResponse;
import com.HRMS.HRMS.dto.RegisterResponse;
import com.HRMS.HRMS.model.Employees;
import com.HRMS.HRMS.security.JwtUtil;
import com.HRMS.HRMS.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    EmployeeService employeeService;
    AuthenticationManager authenticationManager;
    JwtUtil jwtUtil;
    EmployeeController(EmployeeService employeeService, AuthenticationManager authenticationManager, JwtUtil jwtUtil){
        this.employeeService=employeeService;
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employees employee){
        String response = employeeService.addEmployee(employee);
        if(response.length()<25){
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse(response));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse(response));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@RequestBody LoginRequest request){
        try{
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication auth = authenticationManager.authenticate(authToken);
            String role = auth.getAuthorities().iterator().next().getAuthority();
            String token = jwtUtil.generateToken(request.getEmail(), role);
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(token, "Login Successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse(null, "Invalid Credentials"));
        }
    }
}
