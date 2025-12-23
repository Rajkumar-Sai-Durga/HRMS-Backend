package com.HRMS.HRMS.security;

import com.HRMS.HRMS.Repository.EmployeeRepo;
import com.HRMS.HRMS.model.Employees;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OtpStore {
    JavaMailSender javaMailSender;
    OtpStore(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    private final Map<String, String> otpMap = new ConcurrentHashMap<>();

    public void saveOtp(String email, String otp){
        otpMap.put(email, otp);
    }

    public String getOtp(String email){
        return otpMap.get(email);
    }

    public void removeOtp(String email){
        otpMap.remove(email);
    }

    public String generateOtp(){
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    public void sendOtp(String email, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password reset otp");
        message.setText("Hello Sir/Ma'am,\n" +
                "\n" +
                "We received a request to reset your account password.\n" +
                "\n" +
                "Your One-Time Password (OTP) is:\n" +
                "\n" +
                " "+otp+"\n" +
                "\n" +
                "This OTP is valid for 5 minutes. Please do not share it with anyone.\n" +
                "\n" +
                "If you did not request a password reset, you can safely ignore this email.\n" +
                "\n" +
                "Regards,\n" +
                "HRMS Support Team\n");
        javaMailSender.send(message);
    }
    public void updatedMail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Changed");
        message.setText("Hello Sir/Ma'am,\n" +
                "\n" +
                "This is to confirm that your account password has been changed successfully.\n" +
                "\n" +
                "If you made this change, no further action is required.\n" +
                "\n" +
                "If you did NOT request this change, please contact our support team immediately or reset your password to secure your account.\n" +
                "\n" +
                "For your security, we recommend keeping your password confidential and avoiding sharing it with anyone.\n" +
                "\n" +
                "Regards,\n" +
                "HRMs Support Team\n");
        javaMailSender.send(message);
    }
}
