package com.bxb.DemoCrud.user.factory;

import com.bxb.DemoCrud.user.Entity.User;
//import com.bxb.DemoCrud.user.UserOperation;
import com.bxb.DemoCrud.user.factory.Operations.*;
import com.bxb.DemoCrud.user.request.UserRequestDTO;
import org.springframework.stereotype.Component;

//import static com.bxb.DemoCrud.user.UserOperation.*;


@Component
public class UserFactory {

    private final CreateUserOperation createUserOperation;
    private final GetUserOperation getUserOperation;
    private final UpdateUserOperation updateUserOperation;
    private final DeleteUserOperation deleteUserOperation;

    public UserFactory(
            CreateUserOperation createUserOperation,
            GetUserOperation getUserOperation,
            UpdateUserOperation updateUserOperation,
            DeleteUserOperation deleteUserOperation) {

        this.createUserOperation = createUserOperation;
        this.getUserOperation = getUserOperation;
        this.updateUserOperation = updateUserOperation;
        this.deleteUserOperation = deleteUserOperation;
    }

    public UserOperation getOperation(UserRequestType requestType) {

        return switch (requestType) {

            case CREATE -> createUserOperation;

            case GET -> getUserOperation;

            case UPDATE -> updateUserOperation;

            case DELETE -> deleteUserOperation;
        };
    }
}
