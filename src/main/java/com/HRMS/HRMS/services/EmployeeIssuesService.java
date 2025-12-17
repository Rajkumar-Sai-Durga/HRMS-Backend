package com.HRMS.HRMS.services;

import com.HRMS.HRMS.Repository.EmployeeIssuesRepo;
import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.dto.employeeIssues.AdminIssueDto;
import com.HRMS.HRMS.dto.employeeIssues.EmployeeIssueDto;
import com.HRMS.HRMS.model.EmployeeIssues;
import com.HRMS.HRMS.model.Employees;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeIssuesService{
    EmployeeIssuesRepo employeeIssuesRepo;
    EmployeeRepo employeeRepo;
    EmployeeIssuesService(EmployeeIssuesRepo employeeIssuesRepo, EmployeeRepo employeeRepo){
        this.employeeIssuesRepo=employeeIssuesRepo;
        this.employeeRepo=employeeRepo;
    }

    public List<EmployeeIssues> postNewIssue(String employeeId, EmployeeIssueDto empDto) {
        Employees emp = employeeRepo.findEmployeeByEmployeeId(employeeId);
        try{
            EmployeeIssues issue = new EmployeeIssues();
            issue.setEmployee(emp);

            issue.setIssue(empDto.getIssue());
            issue.setDescription(empDto.getDescription());
            issue.setCategory(empDto.getCategory());
            issue.setPriority(empDto.getPriority());
            issue.setAppliedAt(new Date());

            issue.setStatus("PENDING");
            issue.setResolvedById(null);
            issue.setResolvedByName(null);
            issue.setResolvedAt(null);
            employeeIssuesRepo.save(issue);
            return employeeIssuesRepo.findAllByEmployeeId(employeeId);
        } catch (Exception e) {
            return null;
        }
    }

    public List<EmployeeIssues> getAllEmployeeIssues(String employeeId) {
        return employeeIssuesRepo.findAllByEmployeeId(employeeId);
    }

    public List<EmployeeIssues> DeleteEmployeeIssues(int issueId, String employeeId) {
        try {
            employeeIssuesRepo.deleteById(issueId);
            return employeeIssuesRepo.findAllByEmployeeId(employeeId);
        }catch (Exception e){
            return null;
        }
    }

    public List<EmployeeIssues> getAllIssues() {
        try{
            return employeeIssuesRepo.findAll();
        } catch (Exception e) {
            return null;
        }
    }

    public List<EmployeeIssues> updateEmployeeIssues(int issueId, String adminId, AdminIssueDto issueDto) {
        Employees admin = employeeRepo.findEmployeeByEmployeeId(adminId);
        try {
            Date date = new Date();
            employeeIssuesRepo.updateByAdmin(issueId,issueDto.getStatus(),admin.getEmployeeId(),admin.getFirstName(), date);
            return employeeIssuesRepo.findAll();
        } catch (Exception e) {
            return null;
        }
    }

}
