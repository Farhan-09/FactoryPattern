package com.bxb.DemoCrud.otp.request;

import lombok.Data;

@Data
public class VerifyOtpRequest {

    private String email;
    private String otp;



}
