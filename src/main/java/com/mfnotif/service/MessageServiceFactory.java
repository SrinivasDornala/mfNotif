package com.mfnotif.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageServiceFactory {
    @Autowired
    EmailService emailService;
    @Autowired
    SMSService smsService;
    @Autowired
    WhatsAppSMSService whatsAppSMSService;

    public MessageService getMessageService(String type){
       if("sms".equals(type)){
           return smsService;
       }else if("email".equals(type)){
           return emailService;
       }
       return whatsAppSMSService;
    }
}
