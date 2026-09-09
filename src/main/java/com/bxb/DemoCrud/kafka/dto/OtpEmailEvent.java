package com.bxb.DemoCrud.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class OtpEmailEvent {
    private String email;
    private String name;
    private String otp;
}
