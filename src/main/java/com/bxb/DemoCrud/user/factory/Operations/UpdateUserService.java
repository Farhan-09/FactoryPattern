package com.bxb.DemoCrud.user.factory.Operations;

import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.Exception.UserNotFoundException;
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
public class UpdateUserService implements UserOperation {

    private final UserRepo userRepository;
    private final UserMapper userMapper;

    @Override
    public UserRequestType getRequest() {
        return UserRequestType.UPDATE;
    }

    @Override
    public UserOperationResponse execute(UserRequest request) {

        User user = userRepository.findById(request.getId())
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User with id " + request.getId() + " not found"
                        ));

        userMapper.updateEntity(user, request);

        userRepository.save(user);

        return UserOperationResponse.builder()
                .message("User updated successfully")
                .build();
    }
}