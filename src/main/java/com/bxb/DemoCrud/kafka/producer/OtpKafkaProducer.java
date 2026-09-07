package com.bxb.DemoCrud.kafka.producer;

import com.bxb.DemoCrud.kafka.model.OtpEmailEvent;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpKafkaProducer {


    private static final String TOPIC = "otp-email";

    private final KafkaTemplate<String, OtpEmailEvent> kafkaTemplate;

    public void sendOtpEmail(String email, String name, String otp) {

        OtpEmailEvent event = new OtpEmailEvent(email, name, otp);

        kafkaTemplate.send(TOPIC, email, event);
    }

}
