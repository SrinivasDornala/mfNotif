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
public class SMSService implements MessageService {

    @Override
    public void sendMessage(String to, String from , String body, String subject){
        Twilio.init(Example.ACCOUNT_SID, Example.AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(from),
                subject+ body)
                .create();
        log.info("Message Sending {} ",message.getStatus());
    }
}
