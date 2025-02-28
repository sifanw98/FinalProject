package com.chuwa.learn.account_service.dto;

import com.chuwa.learn.account_service.client.dto.OrderDTO;
import com.chuwa.learn.account_service.client.dto.PaymentDTO;
import com.chuwa.learn.account_service.dto.account.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDTO {
    private UserDTO user;
    private int totalOrders;
    private List<OrderDTO> recentOrders;
    private List<PaymentDTO> recentPayments;
}
