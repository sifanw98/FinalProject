package com.chuwa.learn.account_service.client.fallback;

import com.chuwa.learn.account_service.client.OrderServiceClient;
import com.chuwa.learn.account_service.client.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class OrderServiceFallback implements OrderServiceClient {

    @Override
    public List<OrderDTO> getUserOrders(Long userId) {
        log.warn("Fallback for getUserOrders called for userId: {}", userId);
        return new ArrayList<>();
    }

    @Override
    public int countUserOrders(Long userId) {
        log.warn("Fallback for countUserOrders called for userId: {}", userId);
        return 0;
    }
}