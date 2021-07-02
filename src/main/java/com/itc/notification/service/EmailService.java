package com.itc.notification.service;

import com.itc.notification.config.email.EmailConfiguration;
import com.itc.notification.service.viewModel.EmailContent;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

@Service
public class EmailService implements Serializable {

    private static final long serialVersionUID = 1L;

    private EmailConfiguration configuration;
    private String templateName;

    public EmailService(EmailConfiguration configuration) {
        this.configuration = configuration;
    }

    public boolean sendEmail(EmailContent emailContent)
            throws MessagingException {
        Session aSession = createEmailSendSession();
        Transport transport = aSession.getTransport();
        try {
            transport.connect();
            MimeMessage message = buildMessage(emailContent, aSession);
            Transport.send(message);
            return true;
        } finally {
            templateName = null;
            transport.close();
        }
    }

    private Session createEmailSendSession() {
        Session aSession = Session.getDefaultInstance(populateEmailServerProperties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(configuration.getSmtpUsername(), configuration.getSmtpPassword());
            }
        });
        return aSession;
    }

    private MimeMessage buildMessage(EmailContent emailContent, Session session)
            throws MessagingException {

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailContent.getFromEmail()));
        message.setSubject(emailContent.getSubject());
        message.addRecipients(Message.RecipientType.TO, emailContent.getToEmail());
        message.setSentDate(new Date());
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(emailContent.getMessageObject(), "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        return message;
    }

    public Properties populateEmailServerProperties(){
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", configuration.isSmtpAuthentication());
        properties.put("mail.smtp.port", configuration.getSmtpPort());
        properties.setProperty("mail.smtp.host", configuration.getSmtpHost());
        properties.put("mail.debug", configuration.isSmtpDebug());
        properties.put("mail.smtp.socketFactory.port", configuration.getSmtpSocketFactoryPort());
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.fallback", configuration.isSmtpSocketfactoryFallback());
        properties.put("mail.smtp.starttls.enable", configuration.isSmtpTlsEnabled());
        return properties;
    }
}
