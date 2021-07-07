package com.mfnotif.service;

import com.twilio.Twilio;
import com.twilio.example.Example;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WhatsAppSMSService implements MessageService {

    @SneakyThrows
    @Override
    public void sendMessage(String to, String from , String body, String subject){
        Twilio.init(Example.ACCOUNT_SID, Example.AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:"+to),//+15005550006"
                new com.twilio.type.PhoneNumber("whatsapp:"+from),//+14155238886"
                subject +body)
                .create();
        log.info("Message Sending {} ",message.getStatus());
    }
}
