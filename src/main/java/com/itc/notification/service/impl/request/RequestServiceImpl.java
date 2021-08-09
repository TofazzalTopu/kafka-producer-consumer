package com.itc.notification.service.impl.request;

import com.itc.notification.config.email.EmailConfiguration;
import com.itc.notification.config.email.MailTrapEmailConfiguration;
import com.itc.notification.constants.AppConstants;
import com.itc.notification.enums.MessageType;
import com.itc.notification.enums.Status;
import com.itc.notification.model.EmailData;
import com.itc.notification.model.Request;
import com.itc.notification.model.RequestDetails;
import com.itc.notification.model.SchedulerInfo;
import com.itc.notification.repository.RequestRepository;
import com.itc.notification.service.*;
import com.itc.notification.service.viewModel.EmailContent;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private final MailTrapEmailConfiguration mailTrapEmailConfiguration;
    private final MailTrapEmailService mailTrapEmailService;

    public RequestServiceImpl(RequestRepository requestRepository, RequestDetailsService requestDetailsService, EmailService emailService, SchedulerInfoService schedulerInfoService, EmailConfiguration configuration, MailTrapEmailConfiguration mailTrapEmailConfiguration, MailTrapEmailService mailTrapEmailService) {
        this.requestRepository = requestRepository;
        this.requestDetailsService = requestDetailsService;
        this.emailService = emailService;
        this.schedulerInfoService = schedulerInfoService;
        this.configuration = configuration;
        this.mailTrapEmailConfiguration = mailTrapEmailConfiguration;
        this.mailTrapEmailService = mailTrapEmailService;
    }

    @Override
    public List<Request> getRequestList() throws Exception {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> sendRequest() throws Exception {
        List<Request> requestList = new ArrayList<>();
        if (checkIfSchedulerIsRunning(AppConstants.REQUEST_SCHEDULER) == false) {
            requestList = requestRepository.findAllByStatusNot(Status.SENT.name());
            requestList.stream().forEach(request -> {
                List<RequestDetails> detailsList = requestDetailsService.getDetailsListByRequestAndStatus(request, Status.SENT.name());
                detailsList.stream().forEach(details -> {
                    send(request, details);
                    details.setStatus(Status.SENT.name());
                });
                request.setRequestDetailsList(detailsList);
                request.setStatus(Status.SENT.name());
                save(request);
            });
            schedulerInfoService.update(AppConstants.REQUEST_SCHEDULER, false);
        }
        return requestList;
    }

    private boolean checkIfSchedulerIsRunning(String requestScheduler) {
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
    public Request save(Request request) {
        requestRepository.save(request);
        request.getRequestDetailsList().stream().forEach(details -> details.setRequest(request));
        requestDetailsService.saveAll(request.getRequestDetailsList());
        return request;
    }

    @Override
    public boolean sendEmailNotification(EmailData emailData) {
        boolean isSent;
        Request request= new Request();
        request.setAppName(emailData.getAppName());
        request.setMessageType(MessageType.EMAIL);

        RequestDetails requestDetails = new RequestDetails();
        requestDetails.setToEmail(emailData.getEmail());
        requestDetails.setMessageData(emailData.getMessage());
        request.setRequestDetailsList(Arrays.asList(requestDetails));

        request = save(request);
        isSent = send(request, requestDetails);

        return isSent;
    }

    public boolean send(Request request, RequestDetails details) {
        boolean isSent = false;
        if (request.getMessageType().equals(MessageType.EMAIL)) {
            EmailContent emailContent = new EmailContent();
            emailContent.setFromEmail(configuration.getSmtpUsername());
            emailContent.setToEmail(details.getToEmail());
            if (details.getTemplate() == null) {
                emailContent.setSubject(details.getMessageData());
                emailContent.setBody(details.getMessageData());
            } else {
                emailContent.setSubject(details.getTemplate().getSubject());
                emailContent.setBody(details.getTemplate().getBody());
                emailContent.setTemplate(true);
            }
            mailTrapEmailService.sendSimpleMessage(emailContent);
            details.setStatus(Status.SENT.name());
            isSent = true;
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
