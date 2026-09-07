package com.bxb.DemoCrud.kafka.producer;

import com.bxb.DemoCrud.kafka.model.OtpEmailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class OtpKafkaProducer {

    private static final String TOPIC = "otp-email";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendOtpEmail(String email, String name, String otp) {

        OtpEmailEvent event =
                new OtpEmailEvent(email, name, otp);

        try {
            String json = objectMapper.writeValueAsString(event);

            kafkaTemplate.send(TOPIC, email, json);

        } catch (Exception e) {
            throw new RuntimeException("Failed to convert OTP event to JSON", e);
        }
    }
}
