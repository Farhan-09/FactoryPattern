package com.bxb.DemoCrud.user.factory.Operations;

import com.bxb.DemoCrud.kafka.producer.OtpKafkaProducer;
import com.bxb.DemoCrud.otp.service.OtpService;
import com.bxb.DemoCrud.user.Exception.DulplicateEmailException;
import com.bxb.DemoCrud.user.factory.UserOperation;
import com.bxb.DemoCrud.user.repository.UserRepo;
import com.bxb.DemoCrud.user.request.UserRequest;
import com.bxb.DemoCrud.user.response.UserOperationResponse;
import com.bxb.DemoCrud.user.util.UserRequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements UserOperation {

    private final UserRepo userRepository;
    private final OtpService otpService;
    private final OtpKafkaProducer otpKafkaProducer;

    @Override
    public UserRequestType getRequest() {
        return UserRequestType.CREATE;
    }

    @Override
    public UserOperationResponse execute(UserRequest request) {

        String email = request.getEmail().trim().toLowerCase();


        if (userRepository.findByEmail(email).isPresent()) {
            throw new DulplicateEmailException("User already exists");
        }

        // generate OTP nd store OTP nd user data in Redis
        String otp = otpService.generateAndStoreOtp(email, request.getName(), request.getPassword());

        // send OTP through Kafkaa
        otpKafkaProducer.sendOtpEmail(email, request.getName(), otp);

        return UserOperationResponse.builder()
                .message("OTP sent successfully")
                .build();
    }
}