package com.example.paymentservice.controller;

import com.example.paymentservice.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/{orderId}")
    public String makePayment(@PathVariable String orderId) {
        paymentService.processPayment(orderId);
        return "Payment for order " + orderId + " is being processed.";
    }
}
