package com.bxb.DemoCrud.user.controller;

import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.request.UserRequestDTO;
import com.bxb.DemoCrud.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;


    @PostMapping("/create")
    public User createUser(@RequestBody UserRequestDTO requestDTO){

        return userService.createUser(requestDTO);
    }
}
