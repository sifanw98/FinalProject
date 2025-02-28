package com.example.item_service.service;

import com.example.item_service.domain.Inventory;
import com.example.item_service.domain.Item;
import com.example.item_service.kafka.*;
import com.example.item_service.repository.ItemRepository;
import com.example.item_service.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryEventProducer inventoryEventProducer;

    public List<Item> getAllItems(String category) {
        if (category != null) {
            return itemRepository.findByCategory(category);
        }
        return itemRepository.findAll();
    }

    public Item getItemById(String itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public Item createItem(Item item) {
        item.setCreatedAt(new Date());
        item.setUpdatedAt(new Date());
        return itemRepository.save(item);
    }

    public Item updateItem(String itemId, Item itemDetails) {
        Item item = getItemById(itemId);
        item.setName(itemDetails.getName());
        item.setPrice(itemDetails.getPrice());
        item.setUpdatedAt(new Date());
        return itemRepository.save(item);
    }

    public void deleteItem(String itemId) {
        itemRepository.deleteById(itemId);
    }

    /**
     * Reserves items in the inventory for a specific order.
     *
     * @param itemId   The unique ID of the item.
     * @param quantity The quantity of the item to reserve.
     * @param orderId  The unique ID of the order.
     */
    @Transactional
    public void reserveItems(String itemId, int quantity, String orderId) {
        logger.info("Reserving {} units of item {} for order {}", quantity, itemId, orderId);

        // Fetch the inventory for the item
        Inventory inventory = inventoryRepository.findByItemId(itemId)
                .orElseThrow(() -> {
                    logger.error("Inventory not found for item {}", itemId);
                    return new RuntimeException("Inventory not found");
                });

        // Check if there are enough available units
        if (inventory.getAvailableUnits() < quantity) {
            logger.warn("Insufficient inventory for item {}. Available: {}, Requested: {}", itemId, inventory.getAvailableUnits(), quantity);
            throw new RuntimeException("Insufficient inventory");
        }

        // Update the inventory
        inventory.setReservedUnits(inventory.getReservedUnits() + quantity);
        inventory.setAvailableUnits(inventory.getAvailableUnits() - quantity);
        inventoryRepository.save(inventory);

        logger.info("Successfully reserved {} units of item {} for order {}", quantity, itemId, orderId);

        // Publish InventoryReservedEvent
        InventoryReservedEvent reservedEvent = new InventoryReservedEvent(itemId, quantity, orderId);
        inventoryEventProducer.publishReservedInventory(reservedEvent);

        // Publish InventoryUpdatedEvent
        InventoryUpdatedEvent updatedEvent = new InventoryUpdatedEvent(
                itemId,
                inventory.getAvailableUnits(),
                inventory.getReservedUnits()
        );
        inventoryEventProducer.publishInventoryUpdate(updatedEvent);

        // Check if the item is out of stock after reservation
        if (inventory.getAvailableUnits() == 0) {
            logger.warn("Item {} is now out of stock", itemId);
            ItemOutOfStockEvent outOfStockEvent = new ItemOutOfStockEvent(itemId, "Item is out of stock");
            inventoryEventProducer.publishItemOutOfStock(outOfStockEvent);
        }
    }

    /**
     * Releases reserved items back into the inventory for a specific order.
     *
     * @param itemId   The unique ID of the item.
     * @param quantity The quantity of the item to release.
     * @param orderId  The unique ID of the order.
     */
    @Transactional
    public void releaseItems(String itemId, int quantity, String orderId) {
        logger.info("Releasing {} units of item {} for order {}", quantity, itemId, orderId);

        // Fetch the inventory for the item
        Inventory inventory = inventoryRepository.findByItemId(itemId)
                .orElseThrow(() -> {
                    logger.error("Inventory not found for item {}", itemId);
                    return new RuntimeException("Inventory not found");
                });

        // Check if there are enough reserved units
        if (inventory.getReservedUnits() < quantity) {
            logger.warn("Insufficient reserved units for item {}. Reserved: {}, Requested: {}", itemId, inventory.getReservedUnits(), quantity);
            throw new RuntimeException("Insufficient reserved units");
        }

        // Update the inventory
        inventory.setReservedUnits(inventory.getReservedUnits() - quantity);
        inventory.setAvailableUnits(inventory.getAvailableUnits() + quantity);
        inventoryRepository.save(inventory);

        logger.info("Successfully released {} units of item {} for order {}", quantity, itemId, orderId);

        // Publish InventoryReleasedEvent
        InventoryReleasedEvent releasedEvent = new InventoryReleasedEvent(itemId, quantity, orderId);
        inventoryEventProducer.publishInventoryReleased(releasedEvent);

        // Publish InventoryUpdatedEvent
        InventoryUpdatedEvent updatedEvent = new InventoryUpdatedEvent(
                itemId,
                inventory.getAvailableUnits(),
                inventory.getReservedUnits()
        );
        inventoryEventProducer.publishInventoryUpdate(updatedEvent);
    }
}