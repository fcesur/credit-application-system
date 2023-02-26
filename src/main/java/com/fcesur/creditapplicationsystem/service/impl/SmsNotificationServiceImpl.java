package com.fcesur.creditapplicationsystem.service.impl;

import com.fcesur.creditapplicationsystem.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SmsNotificationServiceImpl implements NotificationService {
    @Override
    public void send(String address, String message) {

    }
}
