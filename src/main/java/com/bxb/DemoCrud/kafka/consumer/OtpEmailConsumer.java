package com.bxb.DemoCrud.kafka.consumer;


import com.bxb.DemoCrud.kafka.dto.OtpEmailEvent;
import com.bxb.DemoCrud.kafka.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpEmailConsumer {

    private final EmailService emailService;

    @KafkaListener(topics = "otp-email", groupId = "otp-email-group")

    public void consume(OtpEmailEvent emailEvent) {

        try {
            emailService.sendOtpEmail(emailEvent.getEmail(), emailEvent.getName(), emailEvent.getOtp());

            log.info("OTP sent successfully to {}", emailEvent.getEmail());

        } catch (Exception e) {

            log.error("Failed to send OTP email to {}", emailEvent.getEmail(), e);

            throw e;
        }
    }
}