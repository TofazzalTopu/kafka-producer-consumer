package com.itc.notification.service.impl;

import com.itc.notification.model.Request;
import com.itc.notification.model.RequestDetails;
import com.itc.notification.repository.RequestDetailsRepository;
import com.itc.notification.service.RequestDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tofazzal
 */

@Service
public class RequestDetailsServiceImpl implements RequestDetailsService {

    private final RequestDetailsRepository requestDetailsRepository;

    public RequestDetailsServiceImpl(RequestDetailsRepository requestDetailsRepository) {
        this.requestDetailsRepository = requestDetailsRepository;
    }

    @Override
    public List<RequestDetails> getDetailsListByRequestAndStatus(Request request, String status) {
        return requestDetailsRepository.findAllByRequestAndStatus(request, status);
    }

    @Override
    public List<RequestDetails> saveAll(List<RequestDetails> detailsList) {
        return requestDetailsRepository.saveAll(detailsList);
    }
}
