package com.example.itemservice.controller;

import com.example.itemservice.model.Item;
import com.example.itemservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Provides CRUD and inventory operations
 */
@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // Create or update an item
    @PostMapping
    public Item createItem(@RequestBody Item item) {
        // If updating, you can check if the item contains an ID
        return itemService.saveOrUpdate(item);
    }

    // Retrieve all items
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.findAll();
    }

    // Retrieve an item by ID
    @GetMapping("/{id}")
    public Item getItemById(@PathVariable String id) {
        return itemService.findById(id);
    }

    // Delete an item by ID
    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable String id) {
        itemService.deleteById(id);
    }

    // Update inventory: /api/items/{id}/inventory?delta=3
    @PutMapping("/{id}/inventory")
    public Item updateItemInventory(@PathVariable String id,
                                    @RequestParam int delta) {
        return itemService.updateInventory(id, delta);
    }
}
