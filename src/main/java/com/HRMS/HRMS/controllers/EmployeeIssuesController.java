package com.HRMS.HRMS.controllers;

import com.HRMS.HRMS.dto.ObjectResponse;
import com.HRMS.HRMS.dto.RegisterResponse;
import com.HRMS.HRMS.dto.employeeIssues.AdminIssueDto;
import com.HRMS.HRMS.dto.employeeIssues.EmployeeIssueDto;
import com.HRMS.HRMS.model.EmployeeIssues;
import com.HRMS.HRMS.services.EmployeeIssuesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issue")
public class EmployeeIssuesController {
    EmployeeIssuesService employeeIssuesService;
    EmployeeIssuesController(EmployeeIssuesService employeeIssuesService){
        this.employeeIssuesService=employeeIssuesService;
    }

//    employee raise issue
    @PostMapping("/employee/{employeeId}")
    public ResponseEntity<?> postIssue(@PathVariable String employeeId, @RequestBody EmployeeIssueDto empDto){
        List<EmployeeIssues> issuesList = employeeIssuesService.postNewIssue(employeeId, empDto);
        if(issuesList != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(issuesList,"Posted successful"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(null,"Request Failed"));
    }

//    employee all raised issues
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getAllEmployeeIssues(@PathVariable String employeeId){
        List<EmployeeIssues> issuesList = employeeIssuesService.getAllEmployeeIssues(employeeId);
        if(issuesList != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(issuesList,"all issues got successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(null,"Request Failed"));
    }

    //    employee delete raised issues
    @DeleteMapping("/employee/{issueId}/delete")
    public ResponseEntity<?> getAllEmployeeIssues(@PathVariable int issueId, @RequestParam("employeeId") String employeeId){
        List<EmployeeIssues> issuesList = employeeIssuesService.DeleteEmployeeIssues(issueId, employeeId);
        if(issuesList != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(issuesList,"Deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(null,"Request Failed"));
    }

//    admin get all issues
    @GetMapping("/admin/getAll")
    public ResponseEntity<?> getAllIssues(){
        List<EmployeeIssues> issuesList = employeeIssuesService.getAllIssues();
        if(issuesList != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(issuesList,"Deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(null,"Request Failed"));
    }

//    admin solve issue
    @PutMapping("/admin/{issueId}/update")
    public ResponseEntity<?> updateIssueById(@PathVariable int issueId, @RequestParam("adminId") String adminId, @RequestBody AdminIssueDto adminDto){
        System.out.println(issueId+ " " + adminId+" "+adminDto);
        List<EmployeeIssues> issuesList = employeeIssuesService.updateEmployeeIssues(issueId, adminId, adminDto);
        if(issuesList != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(issuesList,"Updated successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(null,"Request Failed"));
    }
}

