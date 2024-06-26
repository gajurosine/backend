package com.rca.rosinenzambaza.template.v1.mailHandling;

public interface EmailService {
    public void sendEmail(String to, String subject, String content);
}
