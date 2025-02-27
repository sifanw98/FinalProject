package com.example.itemservice.service;

import com.example.itemservice.exception.ResourceNotFoundException;
import com.example.itemservice.model.Item;
import com.example.itemservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /**
     * Create / Update an Item
     */
    public Item saveOrUpdate(Item item) {
        // If business logic requires a custom itemId, generate/validate it here
        return itemRepository.save(item);
    }

    /**
     * Retrieve all Items
     */
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    /**
     * Retrieve a single Item by id or itemId
     * This example uses MongoDB's primary key id; if itemId is needed, extend the Repository or modify here
     */
    public Item findById(String id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + id));
    }

    /**
     * Delete an Item by id
     */
    public void deleteById(String id) {
        if (!itemRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item not found with id: " + id);
        }
        itemRepository.deleteById(id);
    }

    /**
     * Update inventory (delta can be positive or negative)
     */
    public Item updateInventory(String id, int delta) {
        Item item = findById(id);
        int newInventory = item.getInventory() + delta;
        if (newInventory < 0) {
            throw new IllegalArgumentException("Inventory cannot be negative.");
        }
        item.setInventory(newInventory);
        return itemRepository.save(item);
    }
}
