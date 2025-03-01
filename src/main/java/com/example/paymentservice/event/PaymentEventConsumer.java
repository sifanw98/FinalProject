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

        if ("CREATED".equals(orderStatus)) {
            paymentService.processPayment(orderId);
        }
    }
}
