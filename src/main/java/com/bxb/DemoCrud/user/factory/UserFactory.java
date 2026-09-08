package com.bxb.DemoCrud.user.factory;

import com.bxb.DemoCrud.user.util.UserRequestType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class UserFactory {

    private final List<UserOperation> userOperations;

    private final Map<UserRequestType, UserOperation> operationMap =
            new HashMap<>();

    @PostConstruct
    void init() {

        userOperations.forEach(operation ->
                operationMap.put(
                        operation.getRequest(),
                        operation
                )
        );
    }

    public UserOperation getOperation(
            final UserRequestType requestType) {

        return operationMap.get(requestType);
    }
}