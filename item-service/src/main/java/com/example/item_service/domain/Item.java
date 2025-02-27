package com.example.item_service.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Document(collection = "items")
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

    public Item() {
    }

    public Item(String itemId, String upc, String name, String description, BigDecimal price, List<String> pictureUrls, String category, String brand, Date createdAt, Date updatedAt) {
        this.itemId = itemId;
        this.upc = upc;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pictureUrls = pictureUrls;
        this.category = category;
        this.brand = brand;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }

    public void setPictureUrls(List<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}