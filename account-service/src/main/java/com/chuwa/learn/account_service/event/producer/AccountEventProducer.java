package com.chuwa.learn.account_service.event.producer;

import com.chuwa.learn.account_service.event.model.UserRegisteredEvent;
import com.chuwa.learn.account_service.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class AccountEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "account-events";

    public void publishUserRegistered(User user) {
        UserRegisteredEvent event = UserRegisteredEvent.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("USER_REGISTERED")
                .timestamp(LocalDateTime.now())
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();

        log.info("Publishing user registered event: {}", event);
        kafkaTemplate.send(TOPIC, event);
    }
}
