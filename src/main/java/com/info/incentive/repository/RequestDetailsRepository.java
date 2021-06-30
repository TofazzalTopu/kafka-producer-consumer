package com.info.incentive.repository;

import com.info.incentive.model.RequestDetails;
import com.info.incentive.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestDetailsRepository extends JpaRepository<RequestDetails, Long> {
    List<RequestDetails> findAllByRequestAndStatus(Request request, String status);
}
