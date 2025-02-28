package com.example.payment_service.kafka;

import java.math.BigDecimal;
import com.example.payment_service.domain.PaymentStatus;

public class RefundProcessedEvent {
    private String refundId;
    private String paymentId;
    private BigDecimal amount;
    private PaymentStatus status; // Use PaymentStatus enum

    // Constructors, Getters, and Setters
}