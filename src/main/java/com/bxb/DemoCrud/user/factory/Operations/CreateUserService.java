package com.bxb.DemoCrud.user.factory.Operations;

import com.bxb.DemoCrud.user.Exception.DulplicateEmailException;
import com.bxb.DemoCrud.user.factory.UserOperation;



import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.factory.UserOperation;
import com.bxb.DemoCrud.user.mapper.UserMapper;
import com.bxb.DemoCrud.user.repository.UserRepo;
import com.bxb.DemoCrud.user.request.UserRequest;
import com.bxb.DemoCrud.user.response.UserOperationResponse;
import com.bxb.DemoCrud.user.response.UserResponse;
import com.bxb.DemoCrud.user.util.UserRequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements UserOperation {

    private final UserRepo userRepository;
    private final UserMapper userMapper;

    @Override
    public UserRequestType getRequest() {
        return UserRequestType.CREATE;
    }

    @Override
    public UserOperationResponse execute(UserRequest request) {


        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DulplicateEmailException("User already exists");
        }
        User user = userMapper.toEntity(request);

        User savedUser = userRepository.save(user);

        return UserOperationResponse.builder()
                .user(userMapper.toResponse(savedUser))
                .message("User created successfully")
                .build();
    }
}