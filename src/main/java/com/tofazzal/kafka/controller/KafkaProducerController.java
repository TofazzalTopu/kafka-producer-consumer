package com.tofazzal.kafka.controller;

import com.tofazzal.kafka.constants.AppConstants;
import com.tofazzal.kafka.model.EmailData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @author Md. Tofazzal Hossain\nDate : 07-07-2021
 */

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/producers")
public class KafkaProducerController {

    @Autowired
    KafkaTemplate<String, EmailData> emailDataKafkaTemplate;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    // Produce to the topic: AppConstants.TOPIC_SMS
    @PostMapping(value = "/sms")
    public void produceTopicSMS(String message) {
        // Produce to the topic: AppConstants.TOPIC_SMS
        kafkaTemplate.send(AppConstants.TOPIC_SMS, message);
        log.info("Produce Message: " + message + "  to the topic:" + AppConstants.TOPIC_SMS + " and group id: " + AppConstants.KAFKA_GROUP_EMAIL);
    }

    // produce data to the topic: AppConstants.TOPIC_EMAIL
    @PostMapping
    public ResponseEntity<EmailData> produceTopicEmail(@RequestBody EmailData emailData) throws Exception {
        log.info("EmailData: " + emailData);

        // produce data to the topic: AppConstants.TOPIC_EMAIL
        emailDataKafkaTemplate.send(AppConstants.TOPIC_EMAIL, emailData);
        log.info("Produce Message: " + emailData + "  to the topic:" + AppConstants.TOPIC_EMAIL + " and group id: " + AppConstants.KAFKA_GROUP_EMAIL);

        return ResponseEntity.ok().body(emailData);
    }

}
