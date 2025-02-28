package com.example.payment_service.kafka;

public class PaymentFailedEvent {
    private String paymentId;
    private String orderId;
    private String errorMessage;

    public PaymentFailedEvent() {
    }

    public PaymentFailedEvent(String paymentId, String orderId, String errorMessage) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.errorMessage = errorMessage;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "PaymentFailedEvent{" +
                "paymentId='" + paymentId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}