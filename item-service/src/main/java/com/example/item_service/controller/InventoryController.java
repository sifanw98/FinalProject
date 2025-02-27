package com.example.item_service.controller;

import com.example.item_service.domain.Inventory;
import com.example.item_service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{itemId}")
    public Inventory getInventory(@PathVariable String itemId) {
        return inventoryService.getInventoryByItemId(itemId);
    }

    @PutMapping("/{itemId}")
    public Inventory updateInventory(@PathVariable String itemId, @RequestParam int availableUnits, @RequestParam int reservedUnits) {
        return inventoryService.updateInventory(itemId, availableUnits, reservedUnits);
    }
}