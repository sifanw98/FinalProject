package com.example.item_service.service;

import com.example.item_service.domain.Item;
import com.example.item_service.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

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
}