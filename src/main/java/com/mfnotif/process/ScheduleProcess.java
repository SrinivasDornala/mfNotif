package com.mfnotif.process;

import com.mfnotif.cache.LoadCache;
import com.mfnotif.model.Message;
import com.mfnotif.model.Notification;
import com.mfnotif.service.MessageServiceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.Queue;

@Component
@Slf4j
@EnableScheduling
public class ScheduleProcess {

    @Autowired
    MessageServiceFactory messageServiceFactory;

    @Scheduled(fixedRate = 10000)
    private void publishNotifications(){
        log.info("Publishing Messages Start");
        Queue<Message> queue= LoadCache.getNotifications();
        if(queue == null || queue.isEmpty())return;
        while(!queue.isEmpty()) {
            Message message=queue.poll();
            try {
                messageServiceFactory.getMessageService(message.getMessageMethod())
                        .sendMessage(message.getMessageRecipient(),message.getMessageSender(),
                                message.getMessageText(), message.getMessageSubject());
                log.info("Messages successfully sent");
            } catch (Exception e) {
               LoadCache.addtoFailedQueue(message);
               log.error("Message sending Failed");
            }
        }
    }
}
