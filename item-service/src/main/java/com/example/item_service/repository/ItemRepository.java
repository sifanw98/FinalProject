package com.example.item_service.repository;

import com.example.item_service.domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findByCategory(String category);
}