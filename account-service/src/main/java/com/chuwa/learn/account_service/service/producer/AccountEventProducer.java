package com.chuwa.learn.account_service.service.producer;

import com.chuwa.learn.account_service.event.UserCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AccountEventProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public AccountEventProducer(KafkaTemplate<String, UserCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUserCreatedEvent(UserCreatedEvent event) {
        // topic name can be "user-topic", for example
        kafkaTemplate.send("user-topic", event);
    }
}
