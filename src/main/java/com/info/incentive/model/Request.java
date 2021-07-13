package com.info.incentive.model;

import com.info.incentive.enums.MessageType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "notification_request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String appName;
    private String fromEmail;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JoinColumn(name="server_id", nullable=true)
    private ServerInfo serverInfo;
    private String status;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private MessageType messageType;
    private String notificationUrl;

    @Transient
    List<RequestDetails> requestDetailsList;
}
