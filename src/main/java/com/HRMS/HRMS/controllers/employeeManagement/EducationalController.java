package com.HRMS.HRMS.controllers.employeeManagement;

import com.HRMS.HRMS.dto.ObjectResponse;
import com.HRMS.HRMS.dto.RegisterResponse;
import com.HRMS.HRMS.dto.empManage.EducationalDto;
import com.HRMS.HRMS.model.employeeManagement.EducationalDetails;
import com.HRMS.HRMS.services.employeeManagement.EducationalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/educational")
public class EducationalController {
    EducationalService educationalService;
    EducationalController(EducationalService educationalService){
        this.educationalService=educationalService;
    }

//    add educational details
    @PostMapping("/{employeeId}")
    public ResponseEntity<?> addEducationalDetails(@PathVariable String employeeId, @RequestBody EducationalDto dto){
        if(educationalService.addEducationalDetails(employeeId, dto)){
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("Added successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse("Request Failed"));
    }

//    get all degrees
    @GetMapping("/get/{employeeId}")
    public ResponseEntity<?> getAllEducationalDetailsById(@PathVariable String employeeId){
        List<EducationalDetails> eduList = educationalService.getAllEducationalDetailsById(employeeId);
        if(eduList != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(eduList, "Request Successful"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(null, "Request failed"));
    }

//    delete degree
    @DeleteMapping("/delete/{degreeId}/details")
    public ResponseEntity<?> deleteDegreeById(@PathVariable int degreeId, @RequestParam("employeeId") String employeeId){
        List<EducationalDetails> eduList = educationalService.deleteDegreeById(degreeId, employeeId);
        if(eduList != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(eduList,"Deleted successful"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(null,"Delete request failed"));
    }
}
