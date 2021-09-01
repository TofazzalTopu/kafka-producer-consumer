package com.tofazzal.kafka.controller;

import com.tofazzal.kafka.constants.AppConstants;
import com.tofazzal.kafka.model.EmailData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Md. Tofazzal Hossain\nDate : 07-07-2021
 */

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/consumers")
public class KafkaConsumerController {

    // Listen/consume from the topic: AppConstants.TOPIC_EMAIL
    @KafkaListener(topics = AppConstants.TOPIC_EMAIL, groupId = AppConstants.KAFKA_GROUP_EMAIL, containerFactory = AppConstants.KAFKA_LISTENER_CONTAINER_FACTORY_EMAIL_DATA)
    public void consume(EmailData emailData) {
        log.info("Consumed Message: " + emailData + "  from the topic:" + AppConstants.TOPIC_EMAIL + " and group id: " + AppConstants.KAFKA_GROUP_EMAIL);
    }

    // Listen or consume from the topic: AppConstants.TOPIC_SMS
    @KafkaListener(topics = AppConstants.TOPIC_SMS, groupId = AppConstants.KAFKA_GROUP_EMAIL, containerFactory = AppConstants.KAFKA_LISTENER_CONTAINER_FACTORY_STRING)
    public void listener(String message) {
        log.info("Consumed Message: " + message + "  from the topic:" + AppConstants.TOPIC_SMS + " and group id: " + AppConstants.KAFKA_GROUP_EMAIL);
    }

}
