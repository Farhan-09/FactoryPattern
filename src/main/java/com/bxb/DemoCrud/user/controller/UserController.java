package com.bxb.DemoCrud.user.controller;

import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.factory.Operations.UserOperation;
import com.bxb.DemoCrud.user.factory.UserFactory;
import com.bxb.DemoCrud.user.request.UserRequestDTO;
import com.bxb.DemoCrud.user.response.UserResponseDTO;
import com.bxb.DemoCrud.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserFactory userFactory;

    public UserController(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> performOperation(
            @RequestBody UserRequestDTO request) {

        UserOperation operation =
                userFactory.getOperation(request.getUserRequestType());

        UserResponseDTO response =
                operation.execute(request);

        return ResponseEntity.ok(response);
    }
}
