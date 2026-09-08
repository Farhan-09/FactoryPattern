package com.bxb.DemoCrud.user.response;

import lombok.*;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserOperationResponse {

    private UserResponse user;

    private List<UserResponse> users;

    private String message;

    private Integer page;

    private Integer size;

    private Long totalElements;

    private Integer totalPages;
}