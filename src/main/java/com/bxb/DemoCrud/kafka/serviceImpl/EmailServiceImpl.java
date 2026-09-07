package com.bxb.DemoCrud.kafka.serviceImpl;

import com.bxb.DemoCrud.kafka.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
private JavaMailSender mailSender;


    @Override
    public void sendOtpEmail(String email, String name, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your OTP Code");
        message.setText("Hello " + name + ",\n\nYour OTP code is: " + otp + "\n\nThank you!");
        mailSender.send(message);
    }
}
