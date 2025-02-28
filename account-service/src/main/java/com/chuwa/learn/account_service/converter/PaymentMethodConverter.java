package com.chuwa.learn.account_service.converter;

import com.chuwa.learn.account_service.dto.account.PaymentMethodDTO;
import com.chuwa.learn.account_service.model.PaymentMethod;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaymentMethodConverter {
    public PaymentMethodDTO toPaymentMethodDTO(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            return null;
        }

        return PaymentMethodDTO.builder()
                .id(paymentMethod.getId())
                .type(paymentMethod.getType())
                .cardHolderName(paymentMethod.getCardHolderName())
                .cardNumber(paymentMethod.getCardNumber())
                .expirationMonth(paymentMethod.getExpirationMonth())
                .expirationYear(paymentMethod.getExpirationYear())
                .cvv(paymentMethod.getCvv())
                .accountNumber(paymentMethod.getAccountNumber())
                .routingNumber(paymentMethod.getRoutingNumber())
                .paypalEmail(paymentMethod.getPaypalEmail())
                .build();
    }

    public PaymentMethod toPaymentMethod(PaymentMethodDTO dto) {
        if (dto == null) {
            return null;
        }

        return PaymentMethod.builder()
                .id(dto.getId())
                .type(dto.getType())
                .cardHolderName(dto.getCardHolderName())
                .cardNumber(dto.getCardNumber())
                .expirationMonth(dto.getExpirationMonth())
                .expirationYear(dto.getExpirationYear())
                .cvv(dto.getCvv())
                .accountNumber(dto.getAccountNumber())
                .routingNumber(dto.getRoutingNumber())
                .paypalEmail(dto.getPaypalEmail())
                .build();
    }

    /**
     * Convert collection of PaymentMethodDTO to Set of PaymentMethod entities.
     */
    public Set<PaymentMethod> toPaymentMethodSet(Set<PaymentMethodDTO> paymentMethodDTOs) {
        if (paymentMethodDTOs == null) {
            return new HashSet<>();
        }

        return paymentMethodDTOs.stream()
                .map(this::toPaymentMethod)
                .collect(Collectors.toSet());
    }
}
