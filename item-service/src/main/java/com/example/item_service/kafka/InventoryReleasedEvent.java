package com.example.item_service.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryReleasedEvent {
    private String itemId;
    private int releasedUnits;
    private UUID orderId;
}