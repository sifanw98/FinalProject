package com.example.item_service.dao;

import com.example.item_service.domain.Inventory;
import java.util.Optional;

public interface InventoryDAO {
    Optional<Inventory> findByItemId(String itemId);
    Inventory save(Inventory inventory);
    void deleteById(Long id);
}