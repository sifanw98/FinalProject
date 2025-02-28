package com.example.payment_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentEventProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPaymentProcessed(PaymentProcessedEvent event) {
        kafkaTemplate.send("payment-processed", event);
    }

    public void publishPaymentFailed(PaymentFailedEvent event) {
        kafkaTemplate.send("payment-failed", event);
    }

    public void publishRefundProcessed(RefundProcessedEvent event) {
        kafkaTemplate.send("refund-processed", event);
    }
}