package com.example.itemservice.service;

import com.example.itemservice.exception.ResourceNotFoundException;
import com.example.itemservice.model.Item;
import com.example.itemservice.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private Item sampleItem;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleItem = new Item();
        sampleItem.setId("123");
        sampleItem.setItemId("ITEM-123");
        sampleItem.setName("TestItem");
        sampleItem.setPrice(BigDecimal.valueOf(9.99));
        sampleItem.setInventory(10);
    }

    @Test
    void testFindByIdFound() {
        when(itemRepository.findById("123")).thenReturn(Optional.of(sampleItem));
        Item found = itemService.findById("123");
        assertNotNull(found);
        assertEquals("123", found.getId());
        verify(itemRepository, times(1)).findById("123");
    }

    @Test
    void testFindByIdNotFound() {
        when(itemRepository.findById("999")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> {
            itemService.findById("999");
        });
    }

    @Test
    void testUpdateInventory() {
        // Assume current inventory is 10, delta = 5 => new inventory = 15
        when(itemRepository.findById("123")).thenReturn(Optional.of(sampleItem));
        when(itemRepository.save(any(Item.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Item updated = itemService.updateInventory("123", 5);
        assertEquals(15, updated.getInventory());
    }

    @Test
    void testUpdateInventoryNegative() {
        when(itemRepository.findById("123")).thenReturn(Optional.of(sampleItem));
        assertThrows(IllegalArgumentException.class, () -> {
            itemService.updateInventory("123", -20);
        });
    }
}
