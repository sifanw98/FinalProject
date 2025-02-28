package com.example.item_service.kafka;

import java.util.Objects;

public class InventoryEvent {
    private String itemId;
    private int availableUnits;
    private int reservedUnits;

    // Default constructor (required for deserialization)
    public InventoryEvent() {
    }

    public InventoryEvent(String itemId, int availableUnits, int reservedUnits) {
        this.itemId = itemId;
        this.availableUnits = availableUnits;
        this.reservedUnits = reservedUnits;
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

    @Override
    public String toString() {
        return "InventoryEvent{" +
                "itemId='" + itemId + '\'' +
                ", availableUnits=" + availableUnits +
                ", reservedUnits=" + reservedUnits +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryEvent that = (InventoryEvent) o;
        return availableUnits == that.availableUnits &&
                reservedUnits == that.reservedUnits &&
                Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, availableUnits, reservedUnits);
    }
}