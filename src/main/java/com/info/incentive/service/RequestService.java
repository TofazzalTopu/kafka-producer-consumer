package com.info.incentive.service;

import com.info.incentive.model.EmailData;
import com.info.incentive.model.Request;

import java.util.List;

public interface RequestService {

    List<Request> getRequestList() throws Exception;
    List<Request> sendRequest() throws Exception;
    Request save(Request request);
    boolean sendEmailNotification(EmailData emailData);
}
