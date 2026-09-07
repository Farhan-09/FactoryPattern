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

    private final StringRedisTemplate stringRedisTemplate;
    private final int OTP_EXPIRATION_MINUTES = 5;

    private String getKey(String email) {
        return "otp" + email.toLowerCase();
    }

    @Override
    public String generateAndStoreOpt(String email) {

        String otp = String.format("%06d", ThreadLocalRandom.current().nextInt(0, 1000000));
        String key = getKey(email);

        stringRedisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(OTP_EXPIRATION_MINUTES));
        return otp;
    }

    @Override
    public boolean verify(String email, String otp) {

        String key = getKey(email);

        String storedOtp =
                stringRedisTemplate.opsForValue().get(key);

        if (storedOtp != null && storedOtp.equals(otp)) {
            stringRedisTemplate.delete(key);
            return true;
        }

        return false;
    }
}
