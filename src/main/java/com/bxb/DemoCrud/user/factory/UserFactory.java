package com.bxb.DemoCrud.user.factory;

import com.bxb.DemoCrud.user.Entity.User;
import org.springframework.stereotype.Component;


@Component
public class UserFactory {

    public User createUser(String name , String email , String password){

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        return user;
    }
}
