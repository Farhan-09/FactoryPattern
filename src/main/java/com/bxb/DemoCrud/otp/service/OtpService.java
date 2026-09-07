package com.bxb.DemoCrud.otp.service;

public interface OtpService {

    String generateAndStoreOpt(String email);
    boolean verify(String email,String opt);

}
