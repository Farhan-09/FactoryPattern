package com.bxb.DemoCrud.user.factory.Operations;

import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.repository.UserRepo;
import com.bxb.DemoCrud.user.request.UserRequestDTO;
import com.bxb.DemoCrud.user.response.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateUserOperation implements UserOperation {

    private final UserRepo userRepo;

    public CreateUserOperation(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserResponseDTO execute(UserRequestDTO request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        User savedUser = userRepo.save(user);

        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }
}