package com.bxb.DemoCrud.user.controller;

import com.bxb.DemoCrud.user.factory.UserFactory;
import com.bxb.DemoCrud.user.request.UserRequest;

import com.bxb.DemoCrud.user.response.UserOperationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserFactory userFactory;

    @PostMapping
    public ResponseEntity<UserOperationResponse> handle(
            @RequestBody final UserRequest request) {

        UserOperationResponse response = userFactory
                .getOperation(request.getRequestType())
                .execute(request);

        return ResponseEntity.ok(response);
    }
}