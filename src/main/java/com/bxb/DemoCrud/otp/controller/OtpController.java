package com.bxb.DemoCrud.otp.controller;


import com.bxb.DemoCrud.kafka.producer.OtpKafkaProducer;
import com.bxb.DemoCrud.otp.request.SendOtpRequest;
import com.bxb.DemoCrud.otp.request.VerifyOtpRequest;
import com.bxb.DemoCrud.otp.service.OtpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;
    private final OtpKafkaProducer otpKafkaProducer;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(
            @Valid @RequestBody SendOtpRequest request) {

        String otp = otpService.generateAndStoreOpt(
                request.getEmail()
        );

        otpKafkaProducer.sendOtpEmail(
                request.getEmail(),
                request.getName(),
                otp
        );

        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyOtp(
            @RequestBody VerifyOtpRequest request) {

        boolean verified = otpService.verify(
                request.getEmail(),
                request.getOtp()
        );

        if (verified) {
            return ResponseEntity.ok("OTP verified successfully");
        }

        return ResponseEntity.badRequest()
                .body("Invalid or expired OTP");
    }
}