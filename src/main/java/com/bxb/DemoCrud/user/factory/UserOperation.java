package com.bxb.DemoCrud.user.factory;

import com.bxb.DemoCrud.user.request.UserRequest;

import com.bxb.DemoCrud.user.response.UserOperationResponse;
import com.bxb.DemoCrud.user.response.UserResponse;

import com.bxb.DemoCrud.user.util.UserRequestType;

public interface UserOperation {
    UserRequestType getRequest();

    UserOperationResponse execute(UserRequest request);
}
