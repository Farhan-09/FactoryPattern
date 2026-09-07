package com.bxb.DemoCrud.user.serviceImpl;

import com.bxb.DemoCrud.user.Entity.User;
import com.bxb.DemoCrud.user.factory.UserFactory;
import com.bxb.DemoCrud.user.repository.UserRepo;
import com.bxb.DemoCrud.user.request.UserRequestDTO;
import com.bxb.DemoCrud.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserFactory userFactory;

    public UserServiceImpl(UserRepo userRepo,
                           UserFactory userFactory) {
        this.userRepo = userRepo;
        this.userFactory = userFactory;
    }

    @Override
    public User createUser(UserRequestDTO requestDTO) {


        User user = userFactory.createUser(requestDTO.getName(),
                requestDTO.getEmail(),
                requestDTO.getPassword());

        return userRepo.save(user);

    }

}