package com.bxb.DemoCrud.user.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private String name;
    private String email;
    private Long id;


}
