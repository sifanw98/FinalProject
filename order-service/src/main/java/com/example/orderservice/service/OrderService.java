package com.example.orderservice.service;

import com.example.orderservice.model.ItemDTO;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderItem;
import com.example.orderservice.model.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.repository.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ItemServiceClient itemServiceClient;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        OrderItemRepository orderItemRepository,
                        ItemServiceClient itemServiceClient,
                        KafkaProducerService kafkaProducerService) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.itemServiceClient = itemServiceClient;
        this.kafkaProducerService = kafkaProducerService;
    }

    public Order createOrder(UUID userId, List<OrderItem> orderItems) {
        UUID orderId = UUID.randomUUID();
        BigDecimal totalPrice = BigDecimal.ZERO;

        List<OrderItem> enrichedItems = orderItems.stream().map(orderItem -> {
            orderItem.setOrderId(orderId); // set foreign key
            ItemDTO itemDetails = itemServiceClient.getItemById(orderItem.getItemId());
            orderItem.setPrice(itemDetails.getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        for (OrderItem orderItem : enrichedItems) {
            BigDecimal itemTotal = orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
            totalPrice = totalPrice.add(itemTotal);
        }

        Order order = new Order();
        order.setOrderId(orderId);
        order.setUserId(userId);
        order.setStatus(OrderStatus.CREATED);
        order.setTotalPrice(totalPrice);
        order.setCreatedAt(Instant.now());
        order.setUpdatedAt(Instant.now());

        orderRepository.save(order);
        orderItemRepository.saveAll(enrichedItems);

        kafkaProducerService.publishOrderCreated(order);
        return order;
    }


    public Order getOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderItem> getOrderItems(UUID orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public Order updateOrder(UUID orderId, OrderStatus status, List<OrderItem> updatedItems) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        order.setUpdatedAt(Instant.now());

        if (updatedItems != null && !updatedItems.isEmpty()) {
            updatedItems.forEach(item -> item.setOrderId(orderId));

            BigDecimal totalPrice = updatedItems.stream()
                    .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            order.setTotalPrice(totalPrice);
            orderItemRepository.saveAll(updatedItems);
        }

        orderRepository.save(order);
        kafkaProducerService.publishOrderUpdated(order);
        return order;
    }


    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.CANCELED);
        order.setUpdatedAt(Instant.now());
        orderRepository.save(order);

        kafkaProducerService.publishOrderCanceled(order);
    }
}

