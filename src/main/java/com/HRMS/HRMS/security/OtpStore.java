package com.HRMS.HRMS.security;

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
        message.setText("Your OTP is "+ otp +" expires in 5mins");
        javaMailSender.send(message);
    }
}
