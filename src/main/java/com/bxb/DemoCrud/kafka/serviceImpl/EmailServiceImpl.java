package com.bxb.DemoCrud.kafka.serviceImpl;

import com.bxb.DemoCrud.kafka.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendOtpEmail(String email, String name, String otp) {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(email);
            message.setSubject("Bxb");
            message.setText(
                    "Hello " + name + ",\n\n" +
                            "Your OTP is: " + otp + "\n\n" +
                            "This OTP is valid for 5 minutes."
            );

            mailSender.send(message);
        }
    }
