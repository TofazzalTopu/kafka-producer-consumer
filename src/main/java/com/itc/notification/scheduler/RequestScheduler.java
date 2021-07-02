package com.itc.notification.scheduler;

import com.itc.notification.service.RequestService;
import com.itc.notification.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import reactor.core.publisher.Flux;

import java.time.Duration;

//@Service
public class RequestScheduler implements InitializingBean {
    public static final Logger LOGGER = LoggerFactory.getLogger(RequestScheduler.class);

    @Value("${itc.email-service.fetch.fetch-frequency:600}")
    private long fetchFrequency;

    private final UserService userService;
    private final RequestService requestService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    public RequestScheduler(UserService userService, RequestService requestService,
                            SimpMessagingTemplate simpMessagingTemplate) {
        this.userService = userService;
        this.requestService = requestService;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @Override
    public void afterPropertiesSet() {
        Flux.interval(Duration.ofSeconds(fetchFrequency))
                .map((n) -> {
                    try {
//                        LOGGER.info(AppConstants.SCHEDULER_STARTED);
                        return userService.getList();
                    } catch (Exception e) {
                    }
                    return 0;
                })
                .subscribe(message -> simpMessagingTemplate.convertAndSend("/topic/request-list", message));
    }
}
