package com.bxb.DemoCrud.user.request;


//import com.bxb.DemoCrud.user.UserOperation;
import com.bxb.DemoCrud.user.factory.UserRequestType;
import lombok.*;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

        private UserRequestType userRequestType;

        private String name;
        private String email;
        private String password;


}
