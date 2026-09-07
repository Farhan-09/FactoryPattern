package com.bxb.DemoCrud.user.factory;

import com.bxb.DemoCrud.user.request.UserRequestDTO;
import com.bxb.DemoCrud.user.response.UserResponseDTO;
import com.bxb.DemoCrud.user.util.UserRequestType;

public interface UserOperation {
    UserRequestType getRequest();

    UserResponseDTO execute(UserRequestDTO request);
}
