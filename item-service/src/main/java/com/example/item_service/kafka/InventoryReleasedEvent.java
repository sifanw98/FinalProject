package com.example.item_service.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReleasedEvent {
    private String itemId;
    private int releasedUnits;
    private String orderId;
}