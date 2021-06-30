package com.info.incentive.config.email;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
//@Service
public class EmailConfiguration {

    @Value("${itc.email-service.notification.web.socket.url}")
    private String notificationWebSocketUrl;

    @Value("${itc.email-service.transport.username}")
    private String smtpUsername;

    @Value("${itc.email-service.transport.password}")
    private String smtpPassword;

    @Value("${itc.email-service.transport.host}")
    private String smtpHost;

    @Value("${itc.email-service.transport.port}")
    private int smtpPort;

    @Value("${itc.email-service.transport.secruity-protocol}")
    private String smtpSecurityProtocol;

    @Value("${itc.email-service.transport.socket.factory.port}")
    private int smtpSocketFactoryPort;

    @Value("${itc.email-service.transport.socket.factory.fallback}")
    private boolean smtpSocketfactoryFallback;

    @Value("${itc.email-service.transport.starttls.enable}")
    private boolean smtpTlsEnabled;

    @Value("${itc.email-service.transport.auth:true}")
    private boolean smtpAuthentication;

    @Value("${itc.email-service.transport.debug:false}")
    private boolean smtpDebug;


    /*@Bean
    public TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // Resolver for TEXT emails
        templateEngine.addTemplateResolver(textTemplateResolver());
        // Resolver for HTML emails (except the editable one)
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        // Resolver for HTML editable emails (which will be treated as a String)
        templateEngine.addTemplateResolver(stringTemplateResolver());
        // Message source, internationalization specific to emails
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }*/
}
