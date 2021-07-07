/*
package com.mfnotif.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfnotif.dao.NotificationDao;
import com.mfnotif.model.Model;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

	@Autowired
    NotificationDao e;
	

    @KafkaListener(topics = "test", groupId = "foo")
    public void receive(ConsumerRecord<String, String> consumerRecord) throws JsonMappingException, JsonProcessingException {
        String mo= new String(consumerRecord.value());
       
        ObjectMapper objectMapper = new ObjectMapper();
        Model m = objectMapper.readValue(mo, Model.class);
        e.save(m);
    }

    
}*/
