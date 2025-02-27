package com.example.item_service.kafka;

import java.util.Objects;

public class OrderCanceledEvent {
    private String orderId;
    private String itemId;
    private int quantity;

    // Default constructor (required for deserialization)
    public OrderCanceledEvent() {
    }

    public OrderCanceledEvent(String orderId, String itemId, int quantity) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderCanceledEvent{" +
                "orderId='" + orderId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCanceledEvent that = (OrderCanceledEvent) o;
        return quantity == that.quantity &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(itemId, that.itemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, itemId, quantity);
    }
}