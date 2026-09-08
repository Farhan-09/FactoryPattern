package com.bxb.DemoCrud.otp.serviceImpl;



import com.bxb.DemoCrud.otp.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private static final int OTP_EXPIRATION_MINUTES = 5;

    private static final String OTP_PREFIX = "otp:";
    private static final String REGISTER_PREFIX = "register:";

    private final StringRedisTemplate stringRedisTemplate;

    private String getOtpKey(String email) {
        return OTP_PREFIX + email.trim().toLowerCase();
    }

    private String getRegisterKey(String email) {
        return REGISTER_PREFIX + email.trim().toLowerCase();
    }

    @Override
    public String generateAndStoreOtp(
            String email,
            String name,
            String password) {

        String normalizedEmail =
                email.trim().toLowerCase();

        // Generate OTP
        String otp = String.format(
                "%06d",
                ThreadLocalRandom.current()
                        .nextInt(1_000_000)
        );

        // Store OTP for 5 minutes
        stringRedisTemplate.opsForValue().set(
                getOtpKey(normalizedEmail),
                otp,
                Duration.ofMinutes(OTP_EXPIRATION_MINUTES)
        );

        // Store temporary registration data
        String userData = name + "|" + password;

        stringRedisTemplate.opsForValue().set(
                getRegisterKey(normalizedEmail),
                userData,
                Duration.ofMinutes(OTP_EXPIRATION_MINUTES)
        );

        return otp;
    }

    @Override
    public boolean isOtpValid(
            String email,
            String otp) {

        if (otp == null || otp.isBlank()) {
            return false;
        }

        String storedOtp =
                stringRedisTemplate.opsForValue()
                        .get(getOtpKey(email));

        return storedOtp != null
                && storedOtp.equals(otp.trim());
    }

    @Override
    public void deleteOtp(String email) {

        stringRedisTemplate.delete(
                getOtpKey(email)
        );
    }

    @Override
    public String getRegistrationData(
            String email) {

        return stringRedisTemplate.opsForValue()
                .get(getRegisterKey(email));
    }

    @Override
    public void deleteRegistrationData(
            String email) {

        stringRedisTemplate.delete(
                getRegisterKey(email)
        );
    }
}