package com.itc.notification.model;

import com.itc.notification.enums.MessageType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String appName;
    private String fromEmail;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne
    @JoinColumn(name="server_id", nullable=false)
    private ServerInfo serverInfo;
    private String status;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private MessageType messageType;
    private String notificationUrl;
}
