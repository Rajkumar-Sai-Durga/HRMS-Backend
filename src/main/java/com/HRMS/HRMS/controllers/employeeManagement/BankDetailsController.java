package com.HRMS.HRMS.controllers.employeeManagement;

import com.HRMS.HRMS.dto.ObjectResponse;
import com.HRMS.HRMS.dto.RegisterResponse;
import com.HRMS.HRMS.dto.empManage.BankDetailsDto;
import com.HRMS.HRMS.model.employeeManagement.BankDetails;
import com.HRMS.HRMS.services.employeeManagement.BankDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankDetailsController {
    BankDetailsService bankDetailsService;
    BankDetailsController(BankDetailsService bankDetailsService){
        this.bankDetailsService=bankDetailsService;
    }

//    add bank details
    @PostMapping("/post/{employeeId}")
    public ResponseEntity<?> postBankDetails(@PathVariable String employeeId, @RequestBody BankDetailsDto dto){
        if(bankDetailsService.postBankDetails(employeeId, dto)){
            return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("Added Successful"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new RegisterResponse("failed to add details"));
    }

//    get bank details
    @GetMapping("/get/{employeeId}")
    public ResponseEntity<?> getAllBankDetails(@PathVariable String employeeId){
        List<BankDetails> bankList = bankDetailsService.getAllBankDetails(employeeId);
        if(bankList != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(bankList,"Added Successful"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(null, "failed to add details"));
    }

//    delete bank details
    @DeleteMapping("/delete/{bankId}/details")
    public ResponseEntity<?> deleteBankDetailsById(@PathVariable int bankId, @RequestParam("employeeId") String employeeId){
        List<BankDetails> bankList = bankDetailsService.deleteBankDetailsById(bankId,employeeId);
        System.out.println(employeeId);
        System.out.println(bankList);
        if(bankList != null){
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(bankList,"Deleted Successful"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse(null, "failed to delete details"));
    }
}
