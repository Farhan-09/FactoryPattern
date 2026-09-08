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

    private final StringRedisTemplate stringRedisTemplate;

    private String getOtpKey(String email) {
        return "otp:" + email.trim().toLowerCase();
    }

    @Override
    public String generateAndStoreOtp(String email) {

        String normalizedEmail = email.trim().toLowerCase();

        String otp = String.format(
                "%06d",
                ThreadLocalRandom.current().nextInt(1_000_000)
        );

        stringRedisTemplate.opsForValue().set(
                getOtpKey(normalizedEmail),
                otp,
                Duration.ofMinutes(OTP_EXPIRATION_MINUTES)
        );

        return otp;
    }

    @Override
    public boolean isOtpValid(String email, String otp) {

        if (otp == null || otp.isBlank()) {
            return false;
        }

        String storedOtp = stringRedisTemplate.opsForValue()
                .get(getOtpKey(email));

        return storedOtp != null && storedOtp.equals(otp.trim());
    }

    @Override
    public void deleteOtp(String email) {

        stringRedisTemplate.delete(
                getOtpKey(email)
        );
    }
}