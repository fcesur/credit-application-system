package com.fcesur.creditapplicationsystem.service.impl;

import com.fcesur.creditapplicationsystem.entity.CreditNotification;
import com.fcesur.creditapplicationsystem.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class SmsNotificationServiceImpl implements NotificationService {

    private final Logger logger = LoggerFactory.getLogger(SmsNotificationServiceImpl.class);


    @KafkaListener(topics = "sms-notifications", groupId = "notifier-group")
    @Override
    public void send(CreditNotification notification) {
        // send sms
        logger.info("To:" + notification.getPhone() + " Your credit application is approved. Credit Limit : " + notification.getCredit());
    }
}
