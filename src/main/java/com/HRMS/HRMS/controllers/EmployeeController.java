package com.HRMS.HRMS.controllers;

import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.dto.*;
import com.HRMS.HRMS.model.Employees;
import com.HRMS.HRMS.security.JwtUtil;
import com.HRMS.HRMS.security.OtpStore;
import com.HRMS.HRMS.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    EmployeeRepo employeeRepo;
    OtpStore otpStore;
    PasswordEncoder passwordEncoder;
    EmployeeController(EmployeeService employeeService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, EmployeeRepo employeeRepo, OtpStore otpStore, PasswordEncoder passwordEncoder){
        this.employeeService=employeeService;
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
        this.employeeRepo = employeeRepo;
        this.otpStore=otpStore;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employees employee){
        String response = employeeService.addEmployee(employee);
        if(response.length()<25){
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse(response));
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new RegisterResponse(response));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginRequest(@RequestBody LoginRequest request) {

        try {
            System.out.println("STEP 1: Request received");
            System.out.println(request);

            System.out.println("STEP 2: Creating auth token");
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    );

            System.out.println("STEP 3: Authenticating...");
            Authentication auth = authenticationManager.authenticate(authToken);

            System.out.println("STEP 4: Authentication SUCCESS");

            String role = auth.getAuthorities().iterator().next().getAuthority();
            System.out.println("STEP 5: Role = " + role);

            String token = jwtUtil.generateToken(request.getEmail(), role);
            System.out.println("STEP 6: Token generated");

            Employees employee = employeeService.findEmployeeByEmail(request.getEmail());
            System.out.println("STEP 7: Employee fetched");

            employee.setPassword("*********");

            return ResponseEntity.ok(
                    new LoginResponse(token, "Login Successful", employee)
            );

        } catch (Exception e) {
            System.out.println("❌ ERROR OCCURRED");
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(null, "Invalid Credentials", null));
        }
    }

    @PostMapping("/forget-password")
    public ResponseEntity<?> forgetPassword(@RequestBody ForgetPassword fp){
        try {
            try{
                Employees employees = employeeRepo.findEmployeeByEmail(fp.getEmail());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("User not found"));
            }
            String otp = otpStore.generateOtp();
            otpStore.saveOtp(fp.getEmail(), otp);
            otpStore.sendOtp(fp.getEmail(), otp);
            System.out.println("Sent OTP successful");
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("OTP sent"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse("Server is not responding"));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPassword rp){
        try {
            String savedOtp = otpStore.getOtp(rp.getEmail());
            if (savedOtp == null || !savedOtp.equals(rp.getOtp())) {
                return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("Invalid OTP"));
            }
            employeeRepo.updatePasswordByEmail(rp.getEmail(), passwordEncoder.encode(rp.getNewPassword()));
            otpStore.removeOtp(rp.getEmail());
            System.out.println("Password updated successful");
            otpStore.updatedMail(rp.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("Password updated successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse("Something went wrong"));
        }
    }

}
