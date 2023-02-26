package com.fcesur.creditapplicationsystem.service;

import com.fcesur.creditapplicationsystem.request.UserRequest;
import com.fcesur.creditapplicationsystem.response.UserResponse;

public interface AuthenticationService {
    UserResponse save(UserRequest request);
    UserResponse auth(UserRequest request);
}
