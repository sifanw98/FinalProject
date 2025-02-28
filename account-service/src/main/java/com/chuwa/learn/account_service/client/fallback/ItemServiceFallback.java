package com.chuwa.learn.account_service.client.fallback;

import com.chuwa.learn.account_service.client.ItemServiceClient;
import com.chuwa.learn.account_service.client.dto.InventoryDTO;
import com.chuwa.learn.account_service.client.dto.ItemDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ItemServiceFallback implements ItemServiceClient {

    @Override
    public ItemDTO getItemById(Long itemId) {
        log.warn("Fallback for getItemById called for itemId: {}", itemId);
        return null;
    }

    @Override
    public InventoryDTO getItemInventory(Long itemId) {
        log.warn("Fallback for getItemInventory called for itemId: {}", itemId);
        return new InventoryDTO(itemId, 0, 0);
    }

}
