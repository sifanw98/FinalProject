package com.chuwa.learn.account_service.event.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEvent {
    private String eventType;
    private String paymentId;
    private Long orderId;
    private Long userId;
    private BigDecimal amount;
    private String status;
    private LocalDateTime timestamp;
}
