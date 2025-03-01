package com.example.paymentservice.service;

import com.example.paymentservice.event.PaymentEventProducer;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PaymentEventProducer paymentEventProducer;

    public PaymentService(PaymentEventProducer paymentEventProducer) {
        this.paymentEventProducer = paymentEventProducer;
    }

    public void processPayment(String orderId) {
        System.out.println("Processing payment for Order ID: " + orderId);

        paymentEventProducer.sendPaymentEvent(orderId, "PAID");
    }
}
