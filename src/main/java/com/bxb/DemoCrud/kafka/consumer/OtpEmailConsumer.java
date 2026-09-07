package com.bxb.DemoCrud.kafka.consumer;

import com.bxb.DemoCrud.kafka.model.OtpEmailEvent;
import com.bxb.DemoCrud.kafka.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpEmailConsumer {
    private EmailService emailService;

    @KafkaListener(topics = "otp-email", groupId = "otp-email-group")
    public void consume(OtpEmailEvent event) {
        emailService.sendOtpEmail(event.getEmail(), event.getName(), event.getOtp());

    }
}
