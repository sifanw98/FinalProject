package com.example.item_service.kafka;

import com.example.item_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    @Autowired
    private InventoryService inventoryService;

    @KafkaListener(topics = "order-events", groupId = "item-service")
    public void handleOrderCanceled(OrderCanceledEvent event) {
        // Update inventory when an order is canceled
        inventoryService.updateInventory(event.getItemId(), event.getQuantity(), 0);
    }
}