package com.fcesur.creditapplicationsystem.service;

import com.fcesur.creditapplicationsystem.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String findUsername(String jwtToken);

    boolean tokenControl(String jwtToken, UserDetails userDetails);

    String generateToken(User user);
}
