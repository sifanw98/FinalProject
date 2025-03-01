package com.chuwa.learn.account_service.service.impl;

import com.chuwa.learn.account_service.client.OrderServiceClient;
import com.chuwa.learn.account_service.client.PaymentServiceClient;
import com.chuwa.learn.account_service.client.dto.OrderDTO;
import com.chuwa.learn.account_service.client.dto.PaymentDTO;
import com.chuwa.learn.account_service.dto.UserSummaryDTO;
import com.chuwa.learn.account_service.dto.account.UserDTO;
import com.chuwa.learn.account_service.service.UserService;
import com.chuwa.learn.account_service.service.UserSummaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSummaryServiceImpl implements UserSummaryService {

    private final UserService userService;
    private final OrderServiceClient orderServiceClient;
    private final PaymentServiceClient paymentServiceClient;

    @Override
    public UserSummaryDTO getUserSummary(Long userId) {
        Optional<UserDTO> userOpt = userService.getUserById(userId);

        if (userOpt.isEmpty()) {
            log.error("User not found with id: {}", userId);
            return null;
        }

        UserDTO user = userOpt.get();

        List<OrderDTO> recentOrders = Collections.emptyList();
        int totalOrders = 0;

        try {
            recentOrders = orderServiceClient.getUserOrders(userId);
            totalOrders = orderServiceClient.countUserOrders(userId);
        } catch (Exception e) {
            log.error("Error fetching order information for user: {}", userId, e);
        }

        List<PaymentDTO> recentPayments = Collections.emptyList();

        try {
            recentPayments = Collections.singletonList(paymentServiceClient.getPaymentById(String.valueOf(userId)));
        } catch (Exception e) {
            log.error("Error fetching payment information for user: {}", userId, e);
        }

        return UserSummaryDTO.builder()
                .user(user)
                .totalOrders(totalOrders)
                .recentOrders(recentOrders)
                .recentPayments(recentPayments)
                .build();
    }
}
