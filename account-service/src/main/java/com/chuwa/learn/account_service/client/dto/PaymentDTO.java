package com.chuwa.learn.account_service.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private String id;
    private Long orderId;
    private Long userId;
    private BigDecimal amount;
    private String status;
    private String paymentMethod;
}
