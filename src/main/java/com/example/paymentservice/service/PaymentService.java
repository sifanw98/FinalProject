package com.example.paymentservice.service;

import com.example.paymentservice.event.PaymentResultPublisher;
import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentTransaction;
import com.example.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentResultPublisher paymentResultPublisher;

    @Transactional
    public PaymentTransaction processPayment(PaymentRequest request) {
        // Idempotency check
        if (request.getIdempotencyKey() != null) {
            Optional<PaymentTransaction> existing = paymentRepository.findByIdempotencyKey(request.getIdempotencyKey());
            if (existing.isPresent()) return existing.get();
        }

        // Create new transaction
        PaymentTransaction transaction = PaymentTransaction.builder()
                .id(UUID.randomUUID().toString())
                .orderId(request.getOrderId())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .status("PENDING")
                .idempotencyKey(request.getIdempotencyKey())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Save and publish event
        PaymentTransaction saved = paymentRepository.save(transaction);
        paymentResultPublisher.publishPaymentResult(saved.getOrderId(), saved.getStatus());
        return saved;
    }
}