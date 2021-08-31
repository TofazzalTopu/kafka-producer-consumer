package com.tofazzal.kafka.controller;

import com.tofazzal.kafka.constants.AppConstants;
import com.tofazzal.kafka.model.EmailData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;

/**
 * @author Md. Tofazzal Hossain\nDate : 07-07-2021
 */

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/notifications")
public class KafkaController {

    @PostMapping
    @KafkaListener(topics = AppConstants.TOPIC_EMAIL, groupId = AppConstants.KAFKA_GROUP_EMAIL, containerFactory = AppConstants.KAFKA_LISTENER_CONTAINER_FACTORY_EMAIL_DATA)
    public ResponseEntity<EmailData> sendEmailNotification(@RequestBody EmailData emailData) throws Exception {
        log.info("EmailData: " + emailData);
        System.out.println("EmailData: " + emailData);
        return ResponseEntity.ok().body(emailData);
    }

    @KafkaListener(topics = AppConstants.TOPIC_EMAIL, groupId = AppConstants.KAFKA_GROUP_EMAIL, containerFactory = AppConstants.KAFKA_LISTENER_CONTAINER_FACTORY_EMAIL_DATA)
    public void consume(EmailData emailData) {
        log.info("EmailData: " + emailData);
        System.out.println("Consumed Message :" + emailData);
    }

    @KafkaListener(topics = AppConstants.TOPIC_SMS, groupId = AppConstants.KAFKA_GROUP_EMAIL, containerFactory = AppConstants.KAFKA_LISTENER_CONTAINER_FACTORY_STRING)
    public void listen2(String message) {
        log.info("message: " + message);
        System.out.println("Received Messasge in group - group-id: " + message);
    }

}
