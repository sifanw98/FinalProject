package com.example.item_service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryEventProducer {

    private static final Logger logger = LoggerFactory.getLogger(InventoryEventProducer.class);

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * Publishes an InventoryReservedEvent to Kafka.
     *
     * @param event The InventoryReservedEvent to publish.
     */
    public void publishReservedInventory(InventoryReservedEvent event) {
        logger.info("Publishing InventoryReservedEvent: {}", event);
        kafkaTemplate.send("inventory-reserved", event);
    }

    /**
     * Publishes an InventoryReleasedEvent to Kafka.
     *
     * @param event The InventoryReleasedEvent to publish.
     */
    public void publishInventoryReleased(InventoryReleasedEvent event) {
        logger.info("Publishing InventoryReleasedEvent: {}", event);
        kafkaTemplate.send("inventory-released", event);
    }

    /**
     * Publishes an InventoryUpdatedEvent to Kafka.
     *
     * @param event The InventoryUpdatedEvent to publish.
     */
    public void publishInventoryUpdate(InventoryUpdatedEvent event) {
        logger.info("Publishing InventoryUpdatedEvent: {}", event);
        kafkaTemplate.send("inventory-updated", event);
    }

    /**
     * Publishes an ItemOutOfStockEvent to Kafka.
     *
     * @param event The ItemOutOfStockEvent to publish.
     */
    public void publishItemOutOfStock(ItemOutOfStockEvent event) {
        logger.info("Publishing ItemOutOfStockEvent: {}", event);
        kafkaTemplate.send("item-out-of-stock", event);
    }
}

/*
*     public void publishReservedInventory(String itemId, int reservedUnits, String orderId) {
        InventoryReservedEvent event = new InventoryReservedEvent(itemId, reservedUnits, orderId);
        kafkaTemplate.send("inventory-reserved", event);
    }

    public void publishInventoryReleased(String itemId, int releasedUnits, String orderId) {
        InventoryReleasedEvent event = new InventoryReleasedEvent(itemId, releasedUnits, orderId);
        kafkaTemplate.send("inventory-released", event);
    }

    public void publishInventoryUpdate(String itemId, int availableUnits, int reservedUnits) {
        InventoryUpdatedEvent event = new InventoryUpdatedEvent(itemId, availableUnits, reservedUnits);
        kafkaTemplate.send("inventory-updated", event);
    }

    public void publishItemOutOfStock(String itemId) {
        ItemOutOfStockEvent event = new ItemOutOfStockEvent(itemId, "Item is out of stock");
        kafkaTemplate.send("item-out-of-stock", event);
    }
*
* */