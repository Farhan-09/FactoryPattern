package com.bxb.DemoCrud.otp.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class SendOtpRequest {


    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;



}
