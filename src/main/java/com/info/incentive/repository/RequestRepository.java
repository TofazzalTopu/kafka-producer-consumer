package com.info.incentive.repository;

import com.info.incentive.enums.Status;
import com.info.incentive.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findAllByStatusNot(String status);
}
