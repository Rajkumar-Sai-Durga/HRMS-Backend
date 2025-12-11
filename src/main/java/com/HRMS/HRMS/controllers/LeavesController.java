package com.HRMS.HRMS.controllers;

import com.HRMS.HRMS.dto.LeaveDecisionRequest;
import com.HRMS.HRMS.dto.LeaveRequest;
import com.HRMS.HRMS.dto.ObjectResponse;
import com.HRMS.HRMS.dto.RegisterResponse;
import com.HRMS.HRMS.model.Leaves;
import com.HRMS.HRMS.services.LeavesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeavesController {
    LeavesService leavesService;
    LeavesController(LeavesService leavesService){
        this.leavesService=leavesService;
    }

//    apply leaves employee
    @PostMapping("/apply")
    public ResponseEntity<?> applyLeave(@RequestBody LeaveRequest request, Authentication authentication){
        String email = authentication.getName();
        boolean save = leavesService.applieLeave(request, email);
        if(save){
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("Applied successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse("Application failed"));
    }

    //    admin can see allleaves
    @GetMapping("/{employeeId}")
    public ResponseEntity<?> showEmployeeLeaves(@PathVariable String employeeId){
        List<Leaves> allLeaves = leavesService.showEmployeeLeaves(employeeId);
        if(allLeaves != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(allLeaves, "Your leave applications"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(null, "Server is not Responding"));
    }

//    admin approving leaves
    @PutMapping("/applied/{leaveId}")
    public ResponseEntity<?> decisionLeave(@PathVariable int leaveId, @RequestBody LeaveDecisionRequest request, Authentication authentication){
        String email = authentication.getName();
        String status = leavesService.leaveDecision(leaveId, request, email);
        if(status.length()>1){
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("Your Leave application got "+status));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RegisterResponse("Application failed"));
    }

//    admin can see allleaves
    @GetMapping("/all")
    public ResponseEntity<?> showAllLeaves(){
        List<Leaves> allLeaves = leavesService.showAllLeaves();
        if(allLeaves != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(allLeaves, "All the leave applications"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse(null, "Server is not Responding"));
    }
}
