package com.bxb.DemoCrud.otp.service;

public interface OtpService {

     String generateAndStoreOtp(String email);
     boolean verifyOtp(String email, String otp);
}
