package com.itc.notification.service;

import com.itc.notification.model.EmailData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    final String emailTopic = "email-topic";
    final String itemTopic = "item-topic";

//    @KafkaListener(topics = emailTopic, groupId = "sample-group", containerFactory = "kafkaListener")
    public void consume(EmailData emailData) {
        System.out.println("Consumed Message :" + emailData);
    }

    @KafkaListener(topics = itemTopic, groupId = "sample-group", containerFactory = "strKafkaListener")
    public void listen2(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }

}
