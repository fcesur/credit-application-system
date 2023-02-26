package com.fcesur.creditapplicationsystem.controller;


import com.fcesur.creditapplicationsystem.request.UserRequest;
import com.fcesur.creditapplicationsystem.response.UserResponse;
import com.fcesur.creditapplicationsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request) {
        return ResponseEntity.ok(authenticationService.save(request));
    }

    @PostMapping
    public ResponseEntity<UserResponse> authentication(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }

}
