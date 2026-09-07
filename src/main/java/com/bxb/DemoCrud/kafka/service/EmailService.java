package com.bxb.DemoCrud.kafka.service;

public interface EmailService {
    void sendOtpEmail(String email, String name, String otp);
}
