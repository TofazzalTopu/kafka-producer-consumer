package com.info.incentive.controller;

import com.info.incentive.model.Request;
import com.info.incentive.model.User;
import com.info.incentive.service.RequestService;
import com.info.incentive.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Tofazzal
 */


@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;
    private final UserService userService;

    public RequestController(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
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
    public String getNotification(){
        return "Notification received successfully";
    }
}
