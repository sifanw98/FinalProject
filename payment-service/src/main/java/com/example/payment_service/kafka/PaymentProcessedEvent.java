package com.example.payment_service.kafka;

import java.math.BigDecimal;
import com.example.payment_service.domain.PaymentStatus;

public class PaymentProcessedEvent {
    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private PaymentStatus status;

    public PaymentProcessedEvent() {
    }

    public PaymentProcessedEvent(String paymentId, String orderId, BigDecimal amount, PaymentStatus status) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentProcessedEvent{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}