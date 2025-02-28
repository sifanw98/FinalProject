package com.example.payment_service.kafka;

import java.math.BigDecimal;
import com.example.payment_service.domain.PaymentStatus;

public class RefundProcessedEvent {
    private String refundId;
    private String paymentId;
    private BigDecimal amount;
    private PaymentStatus status;

    public RefundProcessedEvent() {
    }

    public RefundProcessedEvent(String refundId, String paymentId, BigDecimal amount, PaymentStatus status) {
        this.refundId = refundId;
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
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
        return "RefundProcessedEvent{" +
                "refundId='" + refundId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }
}