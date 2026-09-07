package com.bxb.DemoCrud.user.service;

import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.request.UserRequestDTO;

public interface UserService {
    User createUser(UserRequestDTO requestDTO);
}
