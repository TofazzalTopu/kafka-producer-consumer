package com.info.incentive.service.viewModel;

import lombok.Data;

@Data
public class EmailContent {

    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
    private boolean isTemplate=false;
    private String messageObject;
}
