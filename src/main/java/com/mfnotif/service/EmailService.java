package com.mfnotif.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Slf4j
@Service
public class EmailService implements MessageService{

    public Properties getProperties(){
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "localhost");
        prop.put("mail.smtp.port", "25");
        return prop;
    }
    @Override
    public void sendMessage(String to, String from , String body, String subject ) throws MessagingException {
        Session session = Session.getDefaultInstance(getProperties());
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients( Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(body, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
        log.info("Message Sent Successfully ");
    }

}
