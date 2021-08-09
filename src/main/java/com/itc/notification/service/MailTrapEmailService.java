package com.itc.notification.service;

import com.itc.notification.config.email.MailTrapEmailConfiguration;
import com.itc.notification.service.viewModel.EmailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailTrapEmailService {
    @Autowired
    private MailTrapEmailConfiguration emailConfiguration;

    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
        mailSenderImpl.setHost(emailConfiguration.getHost());
        mailSenderImpl.setPort(emailConfiguration.getPort());
        mailSenderImpl.setUsername(emailConfiguration.getUsername());
        mailSenderImpl.setPassword(emailConfiguration.getPassword());
        return mailSenderImpl;
    }

    public void sendSimpleMessage(EmailContent emailContent) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailContent.getToEmail());
        message.setSubject(emailContent.getSubject());
        message.setText(emailContent.getBody());
        message.setFrom(emailContent.getFromEmail());
        getJavaMailSender().send(message);
        System.out.println("Success");
    }
}
