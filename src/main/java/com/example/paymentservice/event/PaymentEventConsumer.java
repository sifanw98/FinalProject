package com.example.paymentservice.event;

import com.example.paymentservice.service.PaymentService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class PaymentEventConsumer {

    private final PaymentService paymentService;

    public PaymentEventConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(topics = "order-events", groupId = "payment-service-group")
    public void listenOrderEvents(ConsumerRecord<String, String> record) {
        String orderId = record.key();
        String orderStatus = record.value();

        System.out.println("Received order event: Order ID=" + orderId + ", Status=" + orderStatus);

        // For example, if an order is created, automatically trigger payment
        if ("CREATED".equals(orderStatus)) {
            // You could call service methods that handle the payment logic
            // Or do any other logic as required for your system
            System.out.println("Auto triggering payment for order: " + orderId);
            // ...
        }
    }
}
