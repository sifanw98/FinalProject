package com.chuwa.learn.account_service.client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {
    private Long itemId;
    private int availableQuantity;
    private int reservedQuantity;
}

