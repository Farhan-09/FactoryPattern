package com.bxb.DemoCrud.user.factory.Operations;

import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.factory.UserOperation;
import com.bxb.DemoCrud.user.mapper.UserMapper;
import com.bxb.DemoCrud.user.repository.UserRepo;
import com.bxb.DemoCrud.user.request.UserRequest;
import com.bxb.DemoCrud.user.response.UserOperationResponse;
import com.bxb.DemoCrud.user.response.UserResponse;
import com.bxb.DemoCrud.user.util.UserRequestType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUserService implements UserOperation {

    private final UserRepo userRepository;
    private final UserMapper userMapper;

    @Override
    public UserRequestType getRequest() {
        return UserRequestType.GET_ALL;
    }

    @Override
    public UserOperationResponse execute(UserRequest request) {

        Pageable pageable = PageRequest.of(
                request.getPage(),
                request.getSize()
        );

        Page<User> userPage = userRepository.findAll(pageable);

        List<UserResponse> userResponses = userPage.getContent()
                .stream()
                .map(userMapper::toResponse)
                .toList();

        return UserOperationResponse.builder()
                .users(userResponses)
                .message("Users fetched successfully")
                .page(userPage.getNumber())
                .size(userPage.getSize())
                .totalElements(userPage.getTotalElements())
                .totalPages(userPage.getTotalPages())
                .build();
    }
}
