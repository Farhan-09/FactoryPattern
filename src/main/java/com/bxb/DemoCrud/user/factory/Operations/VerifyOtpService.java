package com.bxb.DemoCrud.user.factory.Operations;



import com.bxb.DemoCrud.otp.service.OtpService;
import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.Exception.DulplicateEmailException;
import com.bxb.DemoCrud.user.factory.UserOperation;
import com.bxb.DemoCrud.user.mapper.UserMapper;
import com.bxb.DemoCrud.user.repository.UserRepo;
import com.bxb.DemoCrud.user.request.UserRequest;
import com.bxb.DemoCrud.user.response.UserOperationResponse;
import com.bxb.DemoCrud.user.util.UserRequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerifyOtpService implements UserOperation {

    private final OtpService otpService;
    private final UserRepo userRepository;
    private final UserMapper userMapper;

    @Override
    public UserRequestType getRequest() {
        return UserRequestType.VERIFY_OTP;
    }

    @Override
    public UserOperationResponse execute(UserRequest request) {

        String email = request.getEmail().trim().toLowerCase();

        boolean valid = otpService.isOtpValid(email, request.getOtp());

        if (!valid) {throw new RuntimeException("Invalid or expired OTP");}

        if (userRepository.findByEmail(email).isPresent()) {
            throw new DulplicateEmailException("User already exists");
        }

        String userData = otpService.getRegistrationData(email);

        if (userData == null) {throw new RuntimeException("Registration data expired");
        }


        String[] data = userData.split("\\|", 2);

        String name = data[0];
        String password = data[1];


        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);


        User savedUser = userRepository.save(user);


        otpService.deleteOtp(email);
        otpService.deleteRegistrationData(email);

        return UserOperationResponse.builder()
                .message("OTP verified and user created successfully")
                .user(userMapper.toResponse(savedUser))
                .build();
    }
}