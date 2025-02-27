package com.example.itemservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "items")  // Specifies the MongoDB collection name (optional)
public class Item {

    @Id
    private String id;                 // MongoDB primary key

    private String itemId;            // External readable/visible ID
    private String name;
    private String upc;               // Universal Product Code
    private BigDecimal price;
    private int inventory;            // Inventory count

    // Additional fields can be added as needed, such as: description, images, attributes, etc.
}
