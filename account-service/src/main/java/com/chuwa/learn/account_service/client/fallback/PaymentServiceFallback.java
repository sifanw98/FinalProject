package com.chuwa.learn.account_service.client.fallback;

import com.chuwa.learn.account_service.client.PaymentServiceClient;
import com.chuwa.learn.account_service.client.dto.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Slf4j
public class PaymentServiceFallback implements PaymentServiceClient {

    @Override
    public PaymentDTO getPaymentById(String paymentId) {
        log.warn("Fallback for getPaymentById called for paymentId: {}", paymentId);
        return null;
    }

    @Override
    public List<PaymentDTO> getUserPayments(Long userId) {
        log.warn("Fallback for getUserPayments called for userId: {}", userId);
        return new ArrayList<>();
    }

}
