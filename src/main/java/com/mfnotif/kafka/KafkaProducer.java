/*
package com.mfnotif.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {


    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        System.out.println("KafkaProducer.send() sending payload='{}' to topic='{}'"+ payload+" "+topic);
        kafkaTemplate.send(topic, payload);
    }

}*/
