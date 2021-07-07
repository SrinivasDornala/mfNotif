package com.mfnotif.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mfnotif.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
public class LoadCache {
    private LoadCache(){}
    private static Queue<Message> queue=null;
    private static Queue<Message> failedQueue = new LinkedList<>();

    public static Queue<Message> loadNotifications() throws IOException {

        try {
            File file = ResourceUtils.getFile("classpath:notifications.json");
            String json = new String(FileCopyUtils.copyToByteArray(file), StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            queue = objectMapper.readValue(json, new TypeReference<LinkedList<Message>>(){});
        }catch (IOException e) {
            log.error("Failed parsing the file", e);
        }
        return queue;
    }

    public static void addtoQueue(Message notification){
        queue.add(notification);
    }
    public static void addtoFailedQueue(Message notification){
        failedQueue.add(notification);
    }

    public static Queue getNotifications(){
        return queue;
    }
}
