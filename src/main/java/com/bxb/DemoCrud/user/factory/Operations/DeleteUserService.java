package com.bxb.DemoCrud.user.factory.Operations;

import com.bxb.DemoCrud.user.factory.UserOperation;
import com.bxb.DemoCrud.user.repository.UserRepo;
import com.bxb.DemoCrud.user.request.UserRequest;
import com.bxb.DemoCrud.user.response.UserOperationResponse;
import com.bxb.DemoCrud.user.util.UserRequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements UserOperation {

    private final UserRepo userRepository;

    @Override
    public UserRequestType getRequest() {
        return UserRequestType.DELETE;
    }

    @Override
    public UserOperationResponse execute(UserRequest request) {

        if (!userRepository.existsById(request.getId())) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(request.getId());

        return UserOperationResponse.builder()
                .message("User deleted successfully")
                .build();
    }
}