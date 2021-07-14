package com.info.incentive.controller;

import com.info.incentive.model.EmailData;
import com.info.incentive.service.RequestService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Boolean> sendEmailNotification(@RequestBody EmailData emailData){
        boolean isSent = requestService.sendEmailNotification(emailData);
        if(isSent){
            simpMessagingTemplate.convertAndSend("/topic/notification" ,"Admin user created successfully with the name "+ emailData.getAppName());
        }
        return ResponseEntity.ok().body(isSent);
    }
}
