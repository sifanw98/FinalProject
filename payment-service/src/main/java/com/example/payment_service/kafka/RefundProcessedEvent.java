package com.example.payment_service.kafka;

import java.math.BigDecimal;

public class RefundProcessedEvent {
    private String refundId;
    private String paymentId;
    private BigDecimal amount;
    private String status; // e.g., "SUCCESS", "FAILED"

    // Constructors, Getters, and Setters
}