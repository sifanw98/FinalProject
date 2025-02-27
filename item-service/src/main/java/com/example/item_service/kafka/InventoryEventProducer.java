package com.example.item_service.kafka;

import com.example.item_service.domain.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventProducer {

    private static final String TOPIC = "inventory-updates";

    @Autowired
    private KafkaTemplate<String, InventoryEvent> kafkaTemplate;

    public void publishInventoryUpdate(Inventory inventory) {
        InventoryEvent event = new InventoryEvent(
                inventory.getItemId(),
                inventory.getAvailableUnits(),
                inventory.getReservedUnits()
        );
        kafkaTemplate.send(TOPIC, event);
    }
}