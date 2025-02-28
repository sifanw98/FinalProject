package com.example.orderservice.service;

import com.example.orderservice.model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "order_created_topic", groupId = "order_group")
    public void consumeOrderCreated(Order order) {
        System.out.println("Received Order Created Event: " + order);
    }

    @KafkaListener(topics = "order_updated_topic", groupId = "order_group")
    public void consumeOrderUpdated(Order order) {
        System.out.println("Received Order Updated Event: " + order);
    }

    @KafkaListener(topics = "order_canceled_topic", groupId = "order_group")
    public void consumeOrderCanceled(Order order) {
        System.out.println("Received Order Canceled Event: " + order);
    }
}
