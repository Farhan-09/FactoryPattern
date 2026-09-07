package com.bxb.DemoCrud.user.mapper;


import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.request.UserRequestDTO;
import com.bxb.DemoCrud.user.response.UserResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return user;
    }

    public UserResponseDTO toResponse(User user) {

        return UserResponseDTO.bui

        ldr()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public void updateEntity(User user, UserRequestDTO request) {

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
    }
}