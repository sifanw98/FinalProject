package com.example.paymentservice.event;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentResultPublisher {
    private static final String TOPIC = "payment-results";
    private final KafkaTemplate<String, PaymentResultEvent> kafkaTemplate;

    public void publishPaymentResult(String orderId, String status) {
        kafkaTemplate.send(TOPIC, new PaymentResultEvent(orderId, status));
    }
}