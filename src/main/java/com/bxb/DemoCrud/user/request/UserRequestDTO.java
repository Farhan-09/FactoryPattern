package com.bxb.DemoCrud.user.request;


import com.bxb.DemoCrud.user.util.UserRequestType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

        private UserRequestType userRequestType;
        private String name;
        private String email;
        private String password;


}
