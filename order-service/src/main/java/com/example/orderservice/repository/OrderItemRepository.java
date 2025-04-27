package com.example.orderservice.repository;

import com.example.orderservice.model.OrderItem;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository extends CassandraRepository<OrderItem, UUID> {
    List<OrderItem> findByOrderId(UUID orderId);
}

