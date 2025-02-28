package com.chuwa.learn.account_service.service.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "payment-service")
public interface PaymentClient {
}
