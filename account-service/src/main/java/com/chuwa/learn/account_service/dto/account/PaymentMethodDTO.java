package com.chuwa.learn.account_service.dto.account;

import com.chuwa.learn.account_service.model.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents user's payment method data transfer object.
 * Depending on PaymentType, some fields (e.g. cardNumber) may or may not be relevant.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethodDTO {

    private Long id;

    @NotNull(message = "Payment type is required")
    private PaymentMethod.PaymentType type;

    private String cardHolderName;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String cvv;

    // For BANK_TRANSFER
    private String accountNumber;
    private String routingNumber;

    // For PAYPAL
    private String paypalEmail;
}
