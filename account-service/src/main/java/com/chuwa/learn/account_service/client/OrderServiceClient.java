package com.chuwa.learn.account_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.chuwa.learn.account_service.client.dto.OrderDTO;
import com.chuwa.learn.account_service.client.fallback.OrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "order-service", fallback = OrderServiceFallback.class)
public interface OrderServiceClient {

    @GetMapping("/api/orders/user/{userId}")
    List<OrderDTO> getUserOrders(@PathVariable("userId") Long userId);

    @GetMapping("/api/orders/count")
    int countUserOrders(@RequestParam("userId") Long userId);
}
