package com.example.paymentservice.service;

import com.example.paymentservice.event.PaymentEventProducer;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.model.PaymentTransaction;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer paymentEventProducer;

    public PaymentService(PaymentRepository paymentRepository,
                          PaymentEventProducer paymentEventProducer) {
        this.paymentRepository = paymentRepository;
        this.paymentEventProducer = paymentEventProducer;
    }


    public PaymentResponse submitPayment(PaymentRequest request) {
        Optional<PaymentTransaction> existingTx =
                paymentRepository.findByIdempotencyKey(request.getIdempotencyKey());

        if (existingTx.isPresent()) {
            return buildResponse(existingTx.get());
        }

        PaymentTransaction paymentTransaction = PaymentTransaction.builder()
                .id(UUID.randomUUID().toString())
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .status("PROCESSING")
                .paymentMethod(request.getPaymentMethod())
                .idempotencyKey(request.getIdempotencyKey())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        PaymentTransaction savedTx = paymentRepository.save(paymentTransaction);
        processPayment(savedTx);
        return buildResponse(savedTx);
    }


    private void processPayment(PaymentTransaction paymentTransaction) {
        paymentTransaction.setStatus("PAID");
        paymentTransaction.setUpdatedAt(LocalDateTime.now());
        paymentRepository.save(paymentTransaction);

        paymentEventProducer.sendPaymentEvent(paymentTransaction.getOrderId(), paymentTransaction.getStatus());
    }


    public PaymentResponse updatePaymentStatus(String paymentId, String newStatus) {
        Optional<PaymentTransaction> txOpt = paymentRepository.findById(paymentId);
        if (txOpt.isEmpty()) {
            throw new RuntimeException("Payment transaction not found: " + paymentId);
        }

        PaymentTransaction transaction = txOpt.get();
        transaction.setStatus(newStatus);
        transaction.setUpdatedAt(LocalDateTime.now());
        paymentRepository.save(transaction);

        paymentEventProducer.sendPaymentEvent(transaction.getOrderId(), newStatus);

        return buildResponse(transaction);
    }


    public PaymentResponse getPayment(String paymentId) {
        return paymentRepository.findById(paymentId)
                .map(this::buildResponse)
                .orElseThrow(() -> new RuntimeException("Payment not found: " + paymentId));
    }


    public PaymentResponse refundPayment(String paymentId, BigDecimal amount) {
        Optional<PaymentTransaction> txOpt = paymentRepository.findById(paymentId);
        if (txOpt.isEmpty()) {
            throw new RuntimeException("Payment transaction not found for refund: " + paymentId);
        }

        PaymentTransaction transaction = txOpt.get();
        transaction.setStatus("REFUND");
        transaction.setUpdatedAt(LocalDateTime.now());
        paymentRepository.save(transaction);
        paymentEventProducer.sendPaymentEvent(transaction.getOrderId(), "REFUND");

        return buildResponse(transaction);
    }


    private PaymentResponse buildResponse(PaymentTransaction transaction) {
        return PaymentResponse.builder()
                .paymentId(transaction.getId())
                .orderId(transaction.getOrderId())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .paymentMethod(transaction.getPaymentMethod())
                .build();
    }
}
