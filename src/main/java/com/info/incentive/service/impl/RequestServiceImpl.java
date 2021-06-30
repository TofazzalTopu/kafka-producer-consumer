package com.info.incentive.service.impl;

import com.info.incentive.config.email.EmailConfiguration;
import com.info.incentive.constants.AppConstants;
import com.info.incentive.enums.MessageType;
import com.info.incentive.enums.Status;
import com.info.incentive.model.Request;
import com.info.incentive.model.RequestDetails;
import com.info.incentive.model.SchedulerInfo;
import com.info.incentive.repository.RequestRepository;
import com.info.incentive.service.EmailService;
import com.info.incentive.service.RequestDetailsService;
import com.info.incentive.service.RequestService;
import com.info.incentive.service.SchedulerInfoService;
import com.info.incentive.service.viewModel.EmailContent;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tofazzal
 */

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestDetailsService requestDetailsService;
    private final EmailService emailService;
    private final SchedulerInfoService schedulerInfoService;
    private final EmailConfiguration configuration;

    public RequestServiceImpl(RequestRepository requestRepository, RequestDetailsService requestDetailsService, EmailService emailService, SchedulerInfoService schedulerInfoService, EmailConfiguration configuration) {
        this.requestRepository = requestRepository;
        this.requestDetailsService = requestDetailsService;
        this.emailService = emailService;
        this.schedulerInfoService = schedulerInfoService;
        this.configuration = configuration;
    }

    @Override
    public List<Request> getRequestList() throws Exception {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> sendRequest() throws Exception {
        List<Request> requestList = new ArrayList<>();
        if (checkSchedulerIsRunning(AppConstants.REQUEST_SCHEDULER) == false) {
            requestList = requestRepository.findAllByStatusNot(Status.SENT.name());
            requestList.stream().forEach(request -> {
                List<RequestDetails> detailsList = requestDetailsService.getDetailsListByRequestAndStatus(request, Status.SENT.name());
                detailsList.stream().forEach(details -> {
                    send(request, details);
                    details.setStatus(Status.SENT.name());
                });
                request.setStatus(Status.SENT.name());
                save(request, detailsList);
            });
            schedulerInfoService.update(AppConstants.REQUEST_SCHEDULER, false);
        }
        return requestList;
    }

    private boolean checkSchedulerIsRunning(String requestScheduler) {
        SchedulerInfo info = schedulerInfoService.findBySchedulerName(AppConstants.REQUEST_SCHEDULER);
        if (info != null) {
            if (!info.isRunning()) {
                schedulerInfoService.update(info.getSchedulerName(), true);
            } else {
                return true;
            }
        } else {
            schedulerInfoService.save(AppConstants.REQUEST_SCHEDULER, true);
        }
        return false;
    }

    @Override
    public Request save(Request request, List<RequestDetails> detailsList) {
        requestRepository.save(request);
        detailsList.stream().forEach(details -> details.setRequest(request));
        requestDetailsService.saveAll(detailsList);
        return request;
    }

    public boolean send(Request request, RequestDetails details) {
        boolean isSent = false;
        if (request.getMessageType().equals(MessageType.EMAIL)) {
            EmailContent emailContent = new EmailContent();
            emailContent.setFromEmail(request.getFromEmail());
            emailContent.setToEmail(details.getToEmail());
            if (details.getTemplate() == null) {
                emailContent.setSubject(details.getMessageData());
                emailContent.setBody(details.getMessageData());
            } else {
                emailContent.setSubject(details.getTemplate().getSubject());
                emailContent.setBody(details.getTemplate().getBody());
                emailContent.setTemplate(true);
            }
            try {
                emailService.sendEmail(emailContent);
                details.setStatus(Status.SENT.name());
                isSent = true;
            } catch (MessagingException e) {
                details.setStatus(Status.FAILED.name());
                e.printStackTrace();
            }
        }
        return isSent;
    }

    public void testSend() {
        EmailContent content = new EmailContent();
        content.setFromEmail(configuration.getSmtpUsername());
        content.setToEmail("manik.mmanik@gmail.com");
        content.setMessageObject("Hi John");
        try {
            emailService.sendEmail(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
