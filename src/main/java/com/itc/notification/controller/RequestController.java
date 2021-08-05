package com.itc.notification.controller;

import com.itc.notification.constants.AppConstants;
import com.itc.notification.model.EmailData;
import com.itc.notification.model.Item;
import com.itc.notification.model.Request;
import com.itc.notification.model.User;
import com.itc.notification.service.RequestService;
import com.itc.notification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tofazzal
 */

@CrossOrigin
@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;
    private final UserService userService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private KafkaTemplate<String, EmailData> emailDataKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public RequestController(RequestService requestService, UserService userService, SimpMessagingTemplate simpMessagingTemplate) {
        this.requestService = requestService;
        this.userService = userService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @GetMapping("/")
    public ResponseEntity<List<Request>> requestList() throws Exception {
        return ResponseEntity.ok().body(requestService.sendRequest());
    }

    @GetMapping
    @MessageMapping("/push-message-mapping")
    @SendTo("/topic/request-list")
    public ResponseEntity<List<User>> userList() throws Exception {
        return ResponseEntity.ok().body(userService.getList());
    }

    @PostMapping
    public ResponseEntity<List<Request>> postRequest(@RequestBody Request request) throws Exception {
        return ResponseEntity.ok().body(requestService.sendRequest());
    }

    @MessageMapping("/notification-message-mapping")
    @SendTo("/topic/notification")
    public String getNotification() {
        simpMessagingTemplate.convertAndSend("/topic/notification", "Notification received successfully");
        return "Notification received successfully";
    }

    @GetMapping(value = "/{message}")
    public void producer(@PathVariable String message) {
        EmailData emailData = new EmailData(message, message, message);
        Item item = new Item(1, "name", "category");
        emailDataKafkaTemplate.send(AppConstants.TOPIC_EMAIL, emailData);
        System.out.println("produce Messasge in group - group-id: " + message);
    }

    @GetMapping(value = "/2")
    public void producer2() {
        kafkaTemplate.send(AppConstants.TOPIC_STRING_MESSAGE, "message");
        System.out.println("produce Messasge in group - group-id: " + "message");
    }

//    @KafkaListener(topics = emailTopic, groupId = AppConstants.KAFKA_EMAIL_DATA_GROUP, containerFactory = AppConstants.KAFKA_LISTENER_CONTAINER_FACTORY)
    public void producer2(@PathVariable EmailData message) {
        emailDataKafkaTemplate.send(AppConstants.TOPIC_STRING_MESSAGE, message);
        System.out.println("produce Messasge in group - group-id: " + message);
    }

}
