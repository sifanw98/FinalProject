package com.example.item_service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Document(collection = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    private String itemId;
    private String upc;
    private String name;
    private String description;
    private BigDecimal price;
    private List<String> pictureUrls;
    private String category;
    private String brand;
    private Date createdAt;
    private Date updatedAt;
}