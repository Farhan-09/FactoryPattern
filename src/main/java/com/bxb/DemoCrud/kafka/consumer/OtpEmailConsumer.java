package com.bxb.DemoCrud.kafka.consumer;

import com.bxb.DemoCrud.kafka.model.OtpEmailEvent;
import com.bxb.DemoCrud.kafka.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class OtpEmailConsumer {

    private final EmailService emailService;
    private final ObjectMapper objectMapper;

    @KafkaListener(
            topics = "otp-email",
            groupId = "otp-email-group"
    )
    public void consume(String message) {

        System.out.println(" KAFKA MESSAGE RECEIVED: " + message);

        try {
            OtpEmailEvent event =
                    objectMapper.readValue(message, OtpEmailEvent.class);

            System.out.println("Sending email to: " + event.getEmail());

            emailService.sendOtpEmail(
                    event.getEmail(),
                    event.getName(),
                    event.getOtp()
            );

            System.out.println(" EMAIL SENT: " + event.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to process OTP event", e);
        }
    }
}