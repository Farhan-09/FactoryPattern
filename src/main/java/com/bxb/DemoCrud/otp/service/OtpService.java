package com.bxb.DemoCrud.otp.service;

public interface OtpService {


        String generateAndStoreOpt(String email);

        boolean verify(String email, String otp);

        void storeRegistrationData(
                String email,
                String name,
                String password
        );

        String[] getRegistrationData(String email);

        void deleteRegistrationData(String email);
    }

