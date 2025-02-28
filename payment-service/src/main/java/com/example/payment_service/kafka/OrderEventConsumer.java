package com.example.payment_service.kafka;

import com.example.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "order-created", groupId = "payment-service")
    public void handleOrderCreated(OrderCreatedEvent event) {
        // Create a payment for the new order
        paymentService.submitPayment(event.getOrderId(), event.getTotalAmount());
    }

    @KafkaListener(topics = "order-canceled", groupId = "payment-service")
    public void handleOrderCanceled(OrderCanceledEvent event) {
        // Reverse payment for the canceled order
        paymentService.reversePayment(event.getOrderId());
    }
}