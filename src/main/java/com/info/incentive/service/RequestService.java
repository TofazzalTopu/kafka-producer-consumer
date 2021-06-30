package com.info.incentive.service;

import com.info.incentive.model.Request;
import com.info.incentive.model.RequestDetails;

import java.util.List;

public interface RequestService {

    List<Request> getRequestList() throws Exception;
    List<Request> sendRequest() throws Exception;
    Request save(Request request, List<RequestDetails> detailsList);
}
