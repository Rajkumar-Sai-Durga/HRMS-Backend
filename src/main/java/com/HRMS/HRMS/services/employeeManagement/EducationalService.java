package com.HRMS.HRMS.services.employeeManagement;

import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.Repository.employeeManagement.EducationalRepo;
import com.HRMS.HRMS.dto.empManage.EducationalDto;
import com.HRMS.HRMS.model.Employees;
import com.HRMS.HRMS.model.employeeManagement.EducationalDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationalService {
    EducationalRepo educationalRepo;
    EmployeeRepo employeeRepo;
    EducationalService(EducationalRepo educationalRepo, EmployeeRepo employeeRepo){
        this.educationalRepo=educationalRepo;
        this.employeeRepo=employeeRepo;
    }

    public boolean addEducationalDetails(String employeeId, EducationalDto dto) {
        Employees emp = employeeRepo.findEmployeeByEmployeeId(employeeId);
        try{
            EducationalDetails details = new EducationalDetails();
            details.setEmployee(emp);
            details.setDegreeType(dto.getDegreeType());
            details.setDegree(dto.getDegree());
            details.setSpecialization(dto.getSpecialization());
            details.setStarted(dto.getStarted());
            details.setEnded(dto.getEnded());
            details.setCgpa(dto.getCgpa());
            educationalRepo.save(details);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<EducationalDetails> getAllEducationalDetailsById(String employeeId) {
        return educationalRepo.getAllEducationalDetailsByEmployeeId(employeeId);
    }

    public List<EducationalDetails> deleteDegreeById(int degreeId, String employeeId) {
        try{
            educationalRepo.deleteById(degreeId);
            return educationalRepo.getAllEducationalDetailsByEmployeeId(employeeId);
        } catch (Exception e) {
            return null;
        }

    }
}
