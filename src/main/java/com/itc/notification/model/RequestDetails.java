package com.itc.notification.model;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "request_details")
public class RequestDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="request_id", nullable=false)
    private Request request;
    private String toEmail;
    private String status;
    @ManyToOne
    @JoinColumn(name="template_id")
    private Template template;
    private String messageData;
    private String report;

}
