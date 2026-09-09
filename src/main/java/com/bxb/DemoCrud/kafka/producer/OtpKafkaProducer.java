package com.bxb.DemoCrud.kafka.producer;


import com.bxb.DemoCrud.kafka.dto.OtpEmailEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class OtpKafkaProducer {

    private static final String TOPIC = "otp-email";

    private final KafkaTemplate<String, OtpEmailEvent> kafkaTemplate;

    public CompletableFuture<Void> sendOtpEmail(String email, String name, String otp) {

        OtpEmailEvent event = new OtpEmailEvent(email, name, otp);

        return kafkaTemplate.send(TOPIC, event).thenAccept(result -> {}).exceptionally(ex -> {
                    throw new RuntimeException("Failed to send OTP email event to Kafka", ex
                    );
                });
    }
}