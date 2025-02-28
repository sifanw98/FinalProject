package com.example.item_service.kafka;

import com.example.item_service.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderEventConsumer.class);

    @Autowired
    private ItemService itemService;

    /**
     * Consumes OrderCreatedEvent from Kafka and reserves items in the inventory.
     *
     * @param event The OrderCreatedEvent to process.
     */
    @KafkaListener(topics = "order-created", groupId = "item-service")
    public void handleOrderCreated(OrderCreatedEvent event) {
        logger.info("Received OrderCreatedEvent: {}", event);
        try {
            // Reserve items in inventory when an order is created
            itemService.reserveItems(event.getItemId(), event.getQuantity(), event.getOrderId());
        } catch (Exception e) {
            logger.error("Error processing OrderCreatedEvent: {}", e.getMessage(), e);
        }
    }

    /**
     * Consumes OrderCanceledEvent from Kafka and releases reserved items in the inventory.
     *
     * @param event The OrderCanceledEvent to process.
     */
    @KafkaListener(topics = "order-canceled", groupId = "item-service")
    public void handleOrderCanceled(OrderCanceledEvent event) {
        logger.info("Received OrderCanceledEvent: {}", event);
        try {
            // Release reserved items when an order is canceled
            itemService.releaseItems(event.getItemId(), event.getQuantity(), event.getOrderId());
        } catch (Exception e) {
            logger.error("Error processing OrderCanceledEvent: {}", e.getMessage(), e);
        }
    }

    @KafkaListener(topics = "order-updated", groupId = "item-service")
    public void handleOrderUpdated(OrderUpdatedEvent event) {
        logger.info("Received OrderUpdatedEvent: {}", event);
        try {
            // Process each item change in the order
            for (OrderUpdatedEvent.OrderItemChange itemChange : event.getItemChanges()) {
                switch (itemChange.getChangeType()) {
                    case ADD:
                        // Reserve inventory for newly added items
                        itemService.reserveItems(itemChange.getItemId(), itemChange.getNewQuantity(), event.getOrderId());
                        break;
                    case REMOVE:
                        // Release inventory for removed items
                        itemService.releaseItems(itemChange.getItemId(), itemChange.getOldQuantity(), event.getOrderId());
                        break;
                    case UPDATE:
                        // Adjust inventory for updated item quantities
                        itemService.adjustInventoryForUpdatedOrder(
                                itemChange.getItemId(),
                                itemChange.getOldQuantity(),
                                itemChange.getNewQuantity(),
                                event.getOrderId()
                        );
                        break;
                    default:
                        logger.warn("Unknown change type: {}", itemChange.getChangeType());
                }
            }
        } catch (Exception e) {
            logger.error("Error processing OrderUpdatedEvent: {}", e.getMessage(), e);
        }
    }
}