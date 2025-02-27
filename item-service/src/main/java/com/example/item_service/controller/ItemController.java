package com.example.item_service.controller;

import com.example.item_service.domain.Item;
import com.example.item_service.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems(@RequestParam(required = false) String category) {
        return itemService.getAllItems(category);
    }

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable String itemId) {
        return itemService.getItemById(itemId);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @PutMapping("/{itemId}")
    public Item updateItem(@PathVariable String itemId, @RequestBody Item itemDetails) {
        return itemService.updateItem(itemId, itemDetails);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable String itemId) {
        itemService.deleteItem(itemId);
    }
}