package com.example.payment_service.kafka;

import java.math.BigDecimal;

public class PaymentProcessedEvent {
    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private String status; // e.g., "SUCCESS", "FAILED"

    // Constructors, Getters, and Setters
}