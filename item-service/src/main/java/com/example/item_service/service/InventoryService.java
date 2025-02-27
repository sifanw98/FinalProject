package com.example.item_service.service;

import com.example.item_service.domain.Inventory;
import com.example.item_service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public Inventory getInventoryByItemId(String itemId) {
        return inventoryRepository.findByItemId(itemId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    public Inventory updateInventory(String itemId, int availableUnits, int reservedUnits) {
        Inventory inventory = getInventoryByItemId(itemId);
        inventory.setAvailableUnits(availableUnits);
        inventory.setReservedUnits(reservedUnits);
        inventory.setUpdatedAt(new Date());
        return inventoryRepository.save(inventory);
    }
}