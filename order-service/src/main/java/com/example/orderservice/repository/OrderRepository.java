package com.example.orderservice.repository;


import com.example.orderservice.model.Order;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface OrderRepository extends CassandraRepository<Order, UUID> {
}
