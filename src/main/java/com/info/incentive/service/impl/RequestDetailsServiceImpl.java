package com.info.incentive.service.impl;

import com.info.incentive.model.Request;
import com.info.incentive.model.RequestDetails;
import com.info.incentive.repository.RequestDetailsRepository;
import com.info.incentive.service.RequestDetailsService;
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
