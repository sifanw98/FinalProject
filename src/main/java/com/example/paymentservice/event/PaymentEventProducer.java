package com.example.paymentservice.event;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public PaymentEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentEvent(String orderId, String paymentStatus) {
        String topic = "payment-events";
        kafkaTemplate.send(topic, orderId, paymentStatus);
        System.out.println("Sent payment event: Order ID=" + orderId + ", Status=" + paymentStatus);
    }
}
