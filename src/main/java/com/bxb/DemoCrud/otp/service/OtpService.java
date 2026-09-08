package com.bxb.DemoCrud.otp.service;



public interface OtpService {

        String generateAndStoreOtp(String email);

        boolean isOtpValid(String email, String otp);

        void deleteOtp(String email);
}