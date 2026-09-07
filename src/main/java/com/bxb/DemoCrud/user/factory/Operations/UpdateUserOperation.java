package com.bxb.DemoCrud.user.factory.Operations;

import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.repository.UserRepo;
import com.bxb.DemoCrud.user.request.UserRequestDTO;
import com.bxb.DemoCrud.user.response.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserOperation implements UserOperation {

    private final UserRepo userRepo;

    public UpdateUserOperation(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserResponseDTO execute(UserRequestDTO request) {

        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found with this email"));

        user.setName(request.getName());

        User updatedUser = userRepo.save(user);

        return UserResponseDTO.builder()
                .id(updatedUser.getId())
                .name(updatedUser.getName())
                .email(updatedUser.getEmail())
                .build();
    }
}