package com.chuwa.learn.account_service.client.dto;

import com.chuwa.learn.account_service.dto.account.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId;
    private String orderNumber;
    private String status;
    private AddressDTO shippingAddress;
}
