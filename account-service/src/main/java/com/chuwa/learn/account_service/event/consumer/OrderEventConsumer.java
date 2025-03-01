package com.chuwa.learn.account_service.event.consumer;

import com.chuwa.learn.account_service.event.model.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ConditionalOnProperty(name = "spring.kafka.enabled", havingValue = "true", matchIfMissing = true)
public class OrderEventConsumer {

    @KafkaListener(topics = "order-events", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeOrderEvent(OrderEvent event) {
        log.info("Consuming order event: {}", event);

        try {
            switch (event.getEventType()) {
                case "ORDER_CREATED":
                    handleOrderCreated(event);
                    break;
                case "ORDER_CANCELED":
                    handleOrderCanceled(event);
                    break;
                default:
                    log.warn("Unknown order event type: {}", event.getEventType());
            }
        } catch (Exception e) {
            log.error("Error processing order event", e);
        }
    }

    private void handleOrderCreated(OrderEvent event) {
        log.info("Order created for user {}: Order ID {}, Total Amount {}",
                event.getUserId(), event.getOrderId(), event.getTotalAmount());

    }

    private void handleOrderCanceled(OrderEvent event) {
        log.info("Order canceled for user {}: Order ID {}",
                event.getUserId(), event.getOrderId());

    }
}
