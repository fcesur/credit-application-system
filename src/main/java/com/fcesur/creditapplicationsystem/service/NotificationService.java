package com.fcesur.creditapplicationsystem.service;

import com.fcesur.creditapplicationsystem.entity.CreditNotification;

public interface NotificationService {
    void send(CreditNotification notification);
}
