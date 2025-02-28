package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, Order> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, Order> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishOrderCreated(Order order) {
        kafkaTemplate.send("order_created_topic", order);
    }

    public void publishOrderUpdated(Order order) {
        kafkaTemplate.send("order_updated_topic", order);
    }

    public void publishOrderCanceled(Order order) {
        kafkaTemplate.send("order_canceled_topic", order);
    }
}
