package com.fcesur.creditapplicationsystem.service.impl;

import com.fcesur.creditapplicationsystem.entity.User;
import com.fcesur.creditapplicationsystem.enums.Role;
import com.fcesur.creditapplicationsystem.repository.UserRepository;
import com.fcesur.creditapplicationsystem.request.UserRequest;
import com.fcesur.creditapplicationsystem.response.UserResponse;
import com.fcesur.creditapplicationsystem.service.AuthenticationService;
import com.fcesur.creditapplicationsystem.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse save(UserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var token = jwtService.generateToken(user);

        return UserResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public UserResponse auth(UserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);

        return UserResponse.builder()
                .token(token)
                .build();
    }

}
