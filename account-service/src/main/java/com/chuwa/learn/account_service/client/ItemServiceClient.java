package com.chuwa.learn.account_service.client;

import com.chuwa.learn.account_service.client.dto.ItemDTO;
import com.chuwa.learn.account_service.client.dto.InventoryDTO;
import com.chuwa.learn.account_service.client.fallback.ItemServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "item-service", fallback = ItemServiceFallback.class)
public interface ItemServiceClient {

    @GetMapping("/api/items/{itemId}")
    ItemDTO getItemById(@PathVariable("itemId") Long itemId);

    @GetMapping("/api/inventory/{itemId}")
    InventoryDTO getItemInventory(@PathVariable("itemId") Long itemId);

}
