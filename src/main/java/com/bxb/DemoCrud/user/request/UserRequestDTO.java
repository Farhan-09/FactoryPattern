package com.bxb.DemoCrud.user.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class UserRequestDTO {


        private String name;
        private String email;
        private String password;


}
