package com.itc.notification.service.impl.kafka;

import com.itc.notification.constants.AppConstants;
import com.itc.notification.model.EmailData;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = AppConstants.TOPIC_EMAIL, groupId = AppConstants.KAFKA_GROUP_EMAIL_DATA, containerFactory = AppConstants.KAFKA_LISTENER_CONTAINER_FACTORY_EMAIL_DATA)
    public void consume(EmailData emailData) {
        System.out.println("Consumed Message :" + emailData);
    }

    @KafkaListener(topics = AppConstants.TOPIC_STRING_MESSAGE, groupId = AppConstants.KAFKA_GROUP_EMAIL_DATA, containerFactory = AppConstants.KAFKA_LISTENER_CONTAINER_FACTORY_STRING)
    public void listen2(String message) {
        System.out.println("Received Messasge in group - group-id: " + message);
    }

}
