package com.HRMS.HRMS.controllers.employeeManagement;

import com.HRMS.HRMS.dto.ObjectResponse;
import com.HRMS.HRMS.dto.RegisterResponse;
import com.HRMS.HRMS.dto.empManage.ProfessionalDetails;
import com.HRMS.HRMS.model.employeeManagement.EmployeeManage;
import com.HRMS.HRMS.services.employeeManagement.ProfessionalDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empManager")
public class ProfessionalDetailsController {
    ProfessionalDetailsService professionalDetailsService;
    ProfessionalDetailsController(ProfessionalDetailsService professionalDetailsService){
        this.professionalDetailsService = professionalDetailsService;
    }

//    employee can add professional details
    @PostMapping("/create/{employeeId}")
    public ResponseEntity<?> addProfessional(@PathVariable String employeeId, @RequestBody ProfessionalDetails pDetails){
        if(professionalDetailsService.addDetails(employeeId, pDetails)){
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("Added successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse("Request failed"));
    }

//    get professional Details
    @GetMapping("/get/{employeeId}")
    public ResponseEntity<?> getprofessional(@PathVariable String employeeId){
        try{
            EmployeeManage emp = professionalDetailsService.getDetails(employeeId);
            if(emp != null){
                return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(emp, "Request Successful"));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(null, "Request Successful not data"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(null, "Request Failed"));
        }
    }

//    update professional details
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<?> updateDetails(@PathVariable String employeeId, @RequestBody ProfessionalDetails pDetails){
        if(professionalDetailsService.updateDetails(employeeId, pDetails)){
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("Update successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse("Request failed"));
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAllEmployees(){
        List<EmployeeManage> listEmployees =professionalDetailsService.getAllEmployees();
        if(listEmployees != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(listEmployees, "all the employees data"));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ObjectResponse(null, "users not found"));
    }
}
