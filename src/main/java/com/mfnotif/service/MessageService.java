package com.mfnotif.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MessageService {

    void sendMessage(String to, String from , String body, String subject) throws MessagingException;
}
