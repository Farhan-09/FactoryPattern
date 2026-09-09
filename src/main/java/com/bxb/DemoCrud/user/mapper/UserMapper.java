package com.bxb.DemoCrud.user.mapper;


import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.request.UserRequest;

import com.bxb.DemoCrud.user.response.UserResponse;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return user;
    }

    public UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public void updateEntity(User user, UserRequest request) {

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
    }
}