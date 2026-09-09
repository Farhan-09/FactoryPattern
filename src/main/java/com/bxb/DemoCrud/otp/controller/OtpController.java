//package com.bxb.DemoCrud.otp.controller;
//
//
//import com.bxb.DemoCrud.kafka.producer.OtpKafkaProducer;
//import com.bxb.DemoCrud.otp.request.RegisterRequest;
//import com.bxb.DemoCrud.otp.service.OtpService;
//import com.bxb.DemoCrud.user.Entity.User;
//import com.bxb.DemoCrud.user.repository.UserRepo;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/otp")
//@RequiredArgsConstructor
//public class OtpController {
//
//    private final OtpService otpService;
//    private final OtpKafkaProducer otpKafkaProducer;
//    private final UserRepo userRepo;
//
//    @PostMapping("/send")
//    public ResponseEntity<String> sendOtp(
//            @Valid @RequestBody RegisterRequest request) {
//
//        String email = request.getEmail()
//                .trim()
//                .toLowerCase();
//
//        if (userRepo.existsByEmail(email)) {
//            return ResponseEntity.badRequest()
//                    .body("Email is already registered");
//        }
//
//        String otp = otpService.generateAndStoreOtp(
//                email,
//                request.getName(),
//                request.getPassword()
//        );
//
//        otpKafkaProducer.sendOtpEmail(
//                email,
//                request.getName(),
//                otp
//        );
//
//        return ResponseEntity.ok(
//                "OTP sent successfully"
//        );
//    }
//
//
//    @PostMapping("/verify")
//    public ResponseEntity<String> verifyOtp(
//            @RequestParam String email,
//            @RequestParam String otp) {
//
//        String normalizedEmail =
//                email.trim().toLowerCase();
//
//        if (!otpService.isOtpValid(
//                normalizedEmail,
//                otp)) {
//
//            return ResponseEntity.badRequest()
//                    .body("Invalid or expired OTP");
//        }
//
//        String userData =
//                otpService.getRegistrationData(
//                        normalizedEmail
//                );
//
//        if (userData == null) {
//            return ResponseEntity.badRequest()
//                    .body("Registration data expired");
//        }
//
//        String[] data = userData.split("\\|", 2);
//
//        User user = new User();
//
//        user.setName(data[0]);
//        user.setEmail(normalizedEmail);
//        user.setPassword(data[1]);
//
//        userRepo.save(user);
//
//        otpService.deleteOtp(normalizedEmail);
//
//        otpService.deleteRegistrationData(
//                normalizedEmail
//        );
//
//        return ResponseEntity.ok(
//                "OTP verified and user created successfully"
//        );
//    }
//}