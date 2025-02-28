package com.example.payment_service.service;

import com.example.payment_service.domain.Payment;
import com.example.payment_service.kafka.PaymentProcessedEvent;
import com.example.payment_service.kafka.PaymentFailedEvent;
import com.example.payment_service.kafka.RefundProcessedEvent;
import com.example.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Payment submitPayment(Payment payment, String idempotencyKey) {
        // Idempotency check
        if (paymentRepository.existsByIdempotencyKey(idempotencyKey)) {
            // If the idempotency key already exists, return the existing payment
            return paymentRepository.findByIdempotencyKey(idempotencyKey)
                    .orElseThrow(() -> new RuntimeException("Duplicate request detected"));
        }

        // Simulate payment processing
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setIdempotencyKey(idempotencyKey); // Store the idempotency key
        paymentRepository.save(payment);

        // Publish PaymentProcessedEvent
        PaymentProcessedEvent event = new PaymentProcessedEvent(
                payment.getPaymentId(),
                payment.getOrderId(),
                payment.getAmount(),
                payment.getStatus().toString()  // Convert enum to string for Kafka
        );
        kafkaTemplate.send("payment-processed", event);

        return payment;
    }

    public Payment updatePayment(String paymentId, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(paymentDetails.getStatus());  // Use PaymentStatus enum
        return paymentRepository.save(payment);
    }

    public Payment reversePayment(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(PaymentStatus.REFUNDED);  // Use PaymentStatus enum

        // Publish RefundProcessedEvent
        RefundProcessedEvent event = new RefundProcessedEvent(
                "REFUND_" + paymentId, // Generate refund ID
                paymentId,
                payment.getAmount(),
                payment.getStatus().toString() // Convert enum to string for Kafka
        );
        kafkaTemplate.send("refund-processed", event);

        return paymentRepository.save(payment);
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}