package com.example.demo.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfig implements PasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, final String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }
}
