package com.HRMS.HRMS.services.employeeManagement;

import com.HRMS.HRMS.Repository.employeeManagement.ProfessionalDetailsRepo;
import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.dto.empManage.ProfessionalDetails;
import com.HRMS.HRMS.model.employeeManagement.EmployeeManage;
import com.HRMS.HRMS.model.Employees;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalDetailsService {
    ProfessionalDetailsRepo professionalDetailsRepo;
    EmployeeRepo employeeRepo;
    ProfessionalDetailsService(ProfessionalDetailsRepo professionalDetailsRepo, EmployeeRepo employeeRepo){
        this.professionalDetailsRepo = professionalDetailsRepo;
        this.employeeRepo=employeeRepo;
    }

    public boolean addDetails(String employeeId, ProfessionalDetails pDetails) {
        try {
            Employees emp = employeeRepo.findEmployeeByEmployeeId(employeeId);
            if(emp == null){
                return false;
            }
            EmployeeManage details = new EmployeeManage();
            details.setEmployee(emp);
            details.setCategory(pDetails.getCategory());
            details.setDepartment(pDetails.getDepartment());
            details.setJobTitle(pDetails.getJobTitle());
            System.out.println(details.toString());
            professionalDetailsRepo.save(details);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public EmployeeManage getDetails(String employeeId) {

        return professionalDetailsRepo.findByEmployeeId(employeeId);
    }

    public boolean updateDetails(String employeeId, ProfessionalDetails pDetails) {
        Employees emp = employeeRepo.findEmployeeByEmployeeId(employeeId);
        try {
            EmployeeManage details = new EmployeeManage();
            details.setEmployee(emp);
            details.setCategory(pDetails.getCategory());
            details.setDepartment(pDetails.getDepartment());
            details.setJobTitle(pDetails.getJobTitle());
            professionalDetailsRepo.updateDetailsByEmployeeId(employeeId, details.getDepartment(), details.getJobTitle(), details.getCategory());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<EmployeeManage> getAllEmployees() {
        return professionalDetailsRepo.findAll();
    }
}
