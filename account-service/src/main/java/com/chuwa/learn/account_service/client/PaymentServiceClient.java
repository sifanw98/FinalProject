package com.chuwa.learn.account_service.client;

import com.chuwa.learn.account_service.client.dto.PaymentDTO;
import com.chuwa.learn.account_service.client.fallback.PaymentServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "payment-service", fallback = PaymentServiceFallback.class)
public interface PaymentServiceClient {

    @GetMapping("/api/payments/{paymentId}")
    PaymentDTO getPaymentById(@PathVariable("paymentId") String paymentId);

    @GetMapping("/api/payments/user/{userId}")
    List<PaymentDTO> getUserPayments(@PathVariable("userId") Long userId);
}
