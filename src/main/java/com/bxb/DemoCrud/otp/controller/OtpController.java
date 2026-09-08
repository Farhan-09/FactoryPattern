package com.bxb.DemoCrud.otp.controller;



import com.bxb.DemoCrud.kafka.producer.OtpKafkaProducer;
import com.bxb.DemoCrud.otp.request.RegisterRequest;
import com.bxb.DemoCrud.otp.service.OtpService;
import com.bxb.DemoCrud.user.repository.UserRepo;
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
    private final UserRepo userRepo;

    @PostMapping("/send")
    public ResponseEntity<String> sendOtp(
            @Valid @RequestBody RegisterRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        if (userRepo.existsByEmail(email)) {
            return ResponseEntity.badRequest()
                    .body("Email is already registered");
        }

        String otp = otpService.generateAndStoreOtp(email);

        otpKafkaProducer.sendOtpEmail(
                email,
                request.getName(),
                otp
        );

        return ResponseEntity.ok("OTP sent successfully");
    }
}