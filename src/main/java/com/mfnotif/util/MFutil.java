package com.mfnotif.util;

import com.mfnotif.model.Message;
import com.mfnotif.model.Notification;
import org.aspectj.weaver.ast.Not;

public class MFutil {
    private MFutil(){}

    public static Message toMessage(Notification notification){
        return new Message().builder().messageMethod(notification.getMethod())
                .messageSender((notification.getMethod()).equals("email")? "mf@mail.com": "+18000010001")
                .messageRecipient(notification.getTo())
                .messageText(notification.getText())
                .messageType(notification.getType())
                .messageSubject(notification.getSubject()).build();
    }
    public static Notification toNotification(Notification  n){
        return new Notification().builder().notify(n.getNotify())
                .method(n.getMethod()).type(n.getType())
                .orderStatus(n.getOrderStatus())
                .subject(n.getSubject()).text(n.getText()).to(n.getTo()).orderId(n.getOrderId()).build();
    }
}
