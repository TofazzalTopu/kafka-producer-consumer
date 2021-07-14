package com.itc.notification.service;

import com.itc.notification.model.Request;
import com.itc.notification.model.RequestDetails;

import java.util.List;

public interface RequestDetailsService {
    List<RequestDetails> getDetailsListByRequestAndStatus(Request request, String status);
    List<RequestDetails> saveAll(List<RequestDetails> detailsList);
}
