package com.bxb.DemoCrud.user.factory.Operations;

import com.bxb.DemoCrud.user.request.UserRequestDTO;
import com.bxb.DemoCrud.user.response.UserResponseDTO;

public interface UserOperation {

    UserResponseDTO execute(UserRequestDTO request);

}