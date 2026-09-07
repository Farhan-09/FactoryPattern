package com.bxb.DemoCrud.user.service;

import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.request.UserRequestDTO;

import java.util.List;

public interface UserService {
    User createUser(UserRequestDTO requestDTO);
//    List<User> getAllUsers();
}
