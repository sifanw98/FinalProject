package com.example.paymentservice.event;

public record PaymentResultEvent(String orderId, String status) {}