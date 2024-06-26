package com.rca.rosinenzambaza.template.v1.mailHandling;

import com.rca.rosinenzambaza.template.v1.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@RequiredArgsConstructor
public class MailService implements EmailService {
    private  JavaMailSender mailSender = ServiceUtils.getEmailSender();
    @Override
    public void sendEmail(String to, String subject,String textMessage) {
       try{
           SimpleMailMessage message = new SimpleMailMessage();
           message.setTo(to);
           message.setFrom("gajurosine1@gmail.com");
           message.setText(textMessage);
           message.setSubject("Transaction Approved!");
           mailSender.send(message);
       }catch (Exception ex){
           ex.printStackTrace();
       }
    }
}
