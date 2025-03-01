package com.example.paymentservice.controller;

import com.example.paymentservice.model.PaymentRequest;
import com.example.paymentservice.model.PaymentResponse;
import com.example.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping
    public PaymentResponse submitPayment(@RequestBody PaymentRequest request) {
        return paymentService.submitPayment(request);
    }


    @PutMapping("/{paymentId}/status")
    public PaymentResponse updatePaymentStatus(
            @PathVariable String paymentId,
            @RequestParam("value") String newStatus) {
        return paymentService.updatePaymentStatus(paymentId, newStatus);
    }


    @PostMapping("/{paymentId}/refund")
    public PaymentResponse refundPayment(
            @PathVariable String paymentId,
            @RequestParam("amount") BigDecimal amount) {
        return paymentService.refundPayment(paymentId, amount);
    }


    @GetMapping("/{paymentId}")
    public PaymentResponse getPayment(@PathVariable String paymentId) {
        return paymentService.getPayment(paymentId);
    }
}
