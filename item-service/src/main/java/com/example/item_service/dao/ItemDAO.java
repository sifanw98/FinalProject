package com.example.item_service.dao;

import com.example.item_service.domain.Item;
import java.util.List;

public interface ItemDAO {
    Item findById(String itemId);
    List<Item> findAll();
    Item save(Item item);
    void deleteById(String itemId);
}