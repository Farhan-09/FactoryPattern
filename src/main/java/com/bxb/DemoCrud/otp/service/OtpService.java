package com.bxb.DemoCrud.otp.service;


public interface OtpService {

        String generateAndStoreOtp(
                String email, String name,
                String password
        );

        boolean isOtpValid(String email, String otp);

        void deleteOtp(String email);

        String getRegistrationData(String email);

        void deleteRegistrationData(String email);
}