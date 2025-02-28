package com.chuwa.learn.account_service.event.consumer;

import com.chuwa.learn.account_service.event.model.PaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ConditionalOnProperty(name = "spring.kafka.enabled", havingValue = "true", matchIfMissing = true)
public class PaymentEventConsumer {

    @KafkaListener(topics = "payment-events", groupId = "${spring.kafka.consumer.group-id}")
    public void consumePaymentEvent(PaymentEvent event) {
        log.info("Consuming payment event: {}", event);

        try {
            switch (event.getEventType()) {
                case "PAYMENT_PROCESSED":
                    handlePaymentProcessed(event);
                    break;
                case "PAYMENT_FAILED":
                    handlePaymentFailed(event);
                    break;
                default:
                    log.warn("Unknown payment event type: {}", event.getEventType());
            }
        } catch (Exception e) {
            log.error("Error processing payment event", e);
        }
    }

    private void handlePaymentProcessed(PaymentEvent event) {
        log.info("Payment processed for user {}: Payment ID {}, Amount {}",
                event.getUserId(), event.getPaymentId(), event.getAmount());
    }

    private void handlePaymentFailed(PaymentEvent event) {
        log.info("Payment failed for user {}: Payment ID {}, Reason: {}",
                event.getUserId(), event.getPaymentId(), event.getStatus());
    }
}
