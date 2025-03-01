package com.example.paymentservice.model;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class PaymentRequest {
    private String orderId;
    private BigDecimal amount;
    private String paymentMethod;
    private String idempotencyKey;
}
