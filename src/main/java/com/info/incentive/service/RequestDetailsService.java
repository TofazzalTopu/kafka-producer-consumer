package com.info.incentive.service;

import com.info.incentive.model.Request;
import com.info.incentive.model.RequestDetails;

import java.util.List;

public interface RequestDetailsService {
    List<RequestDetails> getDetailsListByRequestAndStatus(Request request, String status);
    List<RequestDetails> saveAll(List<RequestDetails> detailsList);
}
