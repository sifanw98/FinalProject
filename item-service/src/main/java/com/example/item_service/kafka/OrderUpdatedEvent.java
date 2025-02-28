package com.example.item_service.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderUpdatedEvent {
    private UUID orderId;
    private List<OrderItemChange> itemChanges;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderItemChange {
        private String itemId;
        private int oldQuantity;
        private int newQuantity;
        private ChangeType changeType;          // Type of change (ADD, REMOVE, UPDATE)
    }

    public enum ChangeType {
        ADD,    // Item was added to the order
        REMOVE, // Item was removed from the order
        UPDATE  // Item quantity was updated
    }
}