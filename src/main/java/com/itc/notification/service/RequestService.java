package com.itc.notification.service;

import com.itc.notification.model.Request;
import com.itc.notification.model.RequestDetails;

import java.util.List;

public interface RequestService {

    List<Request> getRequestList() throws Exception;
    List<Request> sendRequest() throws Exception;
    Request save(Request request, List<RequestDetails> detailsList);
}
