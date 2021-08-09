package com.itc.notification.controller;

import com.itc.notification.constants.AppConstants;
import com.itc.notification.model.EmailData;
import com.itc.notification.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final RequestService requestService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public NotificationController(RequestService requestService, SimpMessagingTemplate simpMessagingTemplate) {
        this.requestService = requestService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    final String emailTopic = "email-topic";

    @PostMapping
    @KafkaListener(topics = emailTopic, groupId = AppConstants.KAFKA_GROUP_EMAIL, containerFactory = AppConstants.KAFKA_LISTENER_CONTAINER_FACTORY_EMAIL_DATA)
    public ResponseEntity<Boolean> sendEmailNotification(@RequestBody EmailData emailData) throws Exception {
        boolean isNotificationSent = requestService.sendEmailNotification(emailData);
        if(isNotificationSent){
            simpMessagingTemplate.convertAndSend("/topic/notification" ,"Admin user created successfully with the name "+ emailData.getAppName());
        }
        return ResponseEntity.ok().body(isNotificationSent);
    }
}
