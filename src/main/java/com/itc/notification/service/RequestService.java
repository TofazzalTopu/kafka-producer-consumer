package com.itc.notification.service;

import com.itc.notification.model.EmailData;
import com.itc.notification.model.Request;

import java.util.List;

public interface RequestService {

    List<Request> getRequestList() throws Exception;
    List<Request> sendRequest() throws Exception;
    Request save(Request request);
    boolean sendEmailNotification(EmailData emailData);
}
