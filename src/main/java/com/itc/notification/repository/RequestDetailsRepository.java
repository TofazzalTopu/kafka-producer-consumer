package com.itc.notification.repository;

import com.itc.notification.model.RequestDetails;
import com.itc.notification.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestDetailsRepository extends JpaRepository<RequestDetails, Long> {
    List<RequestDetails> findAllByRequestAndStatus(Request request, String status);
}
