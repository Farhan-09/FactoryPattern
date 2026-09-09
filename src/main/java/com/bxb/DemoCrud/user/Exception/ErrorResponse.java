package com.bxb.DemoCrud.user.Exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;



@Getter
@Builder
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private int statusCode;
    private String message;
    private String path;
    private Map<String , String> errors;

}

