package com.example.item_service.domain;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id", nullable = false)
    private String itemId;

    @Column(name = "available_units", nullable = false)
    private int availableUnits;

    @Column(name = "reserved_units", nullable = false)
    private int reservedUnits;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    public Inventory() {
    }

    public Inventory(Long id, String itemId, int availableUnits, int reservedUnits, Date createdAt, Date updatedAt) {
        this.id = id;
        this.itemId = itemId;
        this.availableUnits = availableUnits;
        this.reservedUnits = reservedUnits;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getAvailableUnits() {
        return availableUnits;
    }

    public void setAvailableUnits(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    public int getReservedUnits() {
        return reservedUnits;
    }

    public void setReservedUnits(int reservedUnits) {
        this.reservedUnits = reservedUnits;
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