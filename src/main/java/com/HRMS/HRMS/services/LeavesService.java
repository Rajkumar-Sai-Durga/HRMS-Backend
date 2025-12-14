package com.HRMS.HRMS.services;

import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.Repository.LeavesRepo;
import com.HRMS.HRMS.dto.LeaveDecisionRequest;
import com.HRMS.HRMS.dto.LeaveRequest;
import com.HRMS.HRMS.model.Employees;
import com.HRMS.HRMS.model.Leaves;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LeavesService {

    LeavesRepo leavesRepo;
    EmployeeRepo employeeRepo;
    LeavesService(LeavesRepo leavesRepo, EmployeeRepo employeeRepo){
        this.leavesRepo=leavesRepo;
        this.employeeRepo=employeeRepo;
    }

    public boolean applieLeave(LeaveRequest request, String email) {
        Employees employees = employeeRepo.findByEmail(email);
        try{
            Leaves newLeave = new Leaves();

            newLeave.setEmployee(employees);
            newLeave.setUsername(employees.getUsername());

            newLeave.setLeaveType(request.getLeaveType());
            newLeave.setStartDate(request.getStartDate());
            newLeave.setEndDate(request.getEndDate());
            newLeave.setReason(request.getReason());

            newLeave.setStatus("PENDING");
            newLeave.setApprovedBy(null);
            newLeave.setApprovedByName(null);
            newLeave.setAppliedAt(new Date());
            newLeave.setDecisionAt(null);

            leavesRepo.save(newLeave);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateLeave(int leaveId, LeaveRequest request, String email) {
        try{
            leavesRepo.updateLeaveDetails(leaveId, request.getLeaveType(), request.getStartDate(), request.getEndDate(), request.getReason());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteLeaveApplication(int leaveId) {
        try{
            leavesRepo.deleteById(leaveId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String leaveDecision(int leaveId, LeaveDecisionRequest request, String email) {
        Employees admin = employeeRepo.findByEmail(email);
        Leaves leaveInfo = leavesRepo.findById(leaveId).orElse(new Leaves());
        try{
            Leaves newLeave = new Leaves();

            newLeave.setId(leaveInfo.getId());
            newLeave.setEmployee(leaveInfo.getEmployee());
            newLeave.setUsername(leaveInfo.getUsername());
            newLeave.setLeaveType(leaveInfo.getLeaveType());
            newLeave.setStartDate(leaveInfo.getStartDate());
            newLeave.setEndDate(leaveInfo.getEndDate());
            newLeave.setReason(leaveInfo.getReason());
            newLeave.setAppliedAt(leaveInfo.getAppliedAt());

            newLeave.setStatus(request.getStatus());
            newLeave.setApprovedBy(admin.getEmployeeId());
            newLeave.setApprovedByName(admin.getUsername());
            newLeave.setDecisionAt(new Date());

            leavesRepo.save(newLeave);
            return request.getStatus();
        } catch (Exception e) {
            return "";
        }
    }

    public List<Leaves> showAllLeaves() {
        return leavesRepo.findAll();
    }

    public List<Leaves> showEmployeeLeaves(String employeeId) {
        return leavesRepo.findLeaveApplicationsByEmployeeId(employeeId);
    }


    public Leaves getLeaveById(int leaveId) {
        return leavesRepo.findById(leaveId).orElse(null);
    }
}
