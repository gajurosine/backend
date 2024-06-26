package com.rca.rosinenzambaza.template.v1.utils;


import com.rca.rosinenzambaza.template.v1.enums.EVisibility;
import com.rca.rosinenzambaza.template.v1.models.User;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class ServiceUtils {
    // method to check if a user is valid or deleted
    public static boolean isUserDeleted(User user) {
        return user.getVisibility().equals(EVisibility.VOIDED);
    }

    public static JavaMailSenderImpl getEmailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setUsername("mireilleumutoni05@gmail.com");
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPassword("jkgy vbia vjgy snsm");
        mailSender.setPort(587);
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }
}
