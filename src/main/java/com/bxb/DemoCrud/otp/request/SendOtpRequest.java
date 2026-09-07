package com.bxb.DemoCrud.otp.request;

import lombok.Data;

@Data
public class SendOtpRequest {

    private String email;
    private String name;
}
