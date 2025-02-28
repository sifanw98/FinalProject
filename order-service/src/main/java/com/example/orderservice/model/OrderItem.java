package com.example.orderservice.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import java.math.BigDecimal;
import java.util.UUID;

@Table("order_items")
public class OrderItem {
    @PrimaryKeyColumn(name = "order_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private UUID orderId;

    @PrimaryKeyColumn(name = "product_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private UUID productId;

    @Column("quantity")
    private int quantity;

    @Column("price")
    private BigDecimal price;

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

