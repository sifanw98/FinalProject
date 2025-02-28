package com.example.orderservice.controller;

import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.model.OrderStatus;
import com.example.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestParam UUID userId, @RequestBody List<OrderItem> items) {
        return orderService.createOrder(userId, items);
    }

    @PutMapping("/{orderId}/status")
    public Order updateOrder(@PathVariable UUID orderId,
                             @RequestParam OrderStatus status,
                             @RequestBody(required = false) List<OrderItem> updatedItems) {
        return orderService.updateOrder(orderId, status, updatedItems);
    }

    @DeleteMapping("/{orderId}")
    public void cancelOrder(@PathVariable UUID orderId) {
        orderService.cancelOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable UUID orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("/{orderId}/items")
    public List<OrderItem> getOrderItems(@PathVariable UUID orderId) {
        return orderService.getOrderItems(orderId);
    }
}


