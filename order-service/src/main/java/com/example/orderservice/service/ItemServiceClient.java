package com.example.orderservice.service;

import com.example.orderservice.model.ItemDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.UUID;

@FeignClient(name = "item-service")
public interface ItemServiceClient {
    @GetMapping("/items/{id}")
    ItemDTO getItemById(@PathVariable("id") UUID id);
}
