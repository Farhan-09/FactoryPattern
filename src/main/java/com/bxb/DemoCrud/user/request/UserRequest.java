package com.bxb.DemoCrud.user.request;

import com.bxb.DemoCrud.user.util.UserRequestType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private UserRequestType requestType;

    private Long id;

    private String name;

    private String email;

    private String password;

    private Integer page = 0;

    private Integer size = 10;
}