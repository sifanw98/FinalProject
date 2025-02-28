package com.example.payment_service.controller;

import com.example.payment_service.domain.Payment;
import com.example.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment submitPayment(
            @RequestBody Payment payment,
            @RequestHeader("Idempotency-Key") String idempotencyKey) { // Idempotency key from header
        return paymentService.submitPayment(payment, idempotencyKey);
    }

    @PutMapping("/{paymentId}")
    public Payment updatePayment(@PathVariable String paymentId, @RequestBody Payment paymentDetails) {
        return paymentService.updatePayment(paymentId, paymentDetails);
    }

    @DeleteMapping("/{paymentId}")
    public Payment reversePayment(@PathVariable String paymentId) {
        return paymentService.reversePayment(paymentId);
    }

    @GetMapping("/{paymentId}")
    public Payment getPayment(@PathVariable String paymentId) {
        return paymentService.getPayment(paymentId);
    }
}