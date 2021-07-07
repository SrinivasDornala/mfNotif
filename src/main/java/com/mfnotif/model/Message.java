package com.mfnotif.model;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String messageType;
    private String messageText;
    private String messageMethod;
    private String messageRecipient;
    private String messageSender;
    private String messageSubject;
}
