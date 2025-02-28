package com.chuwa.learn.account_service.service.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "item-service")
public interface ItemClient {
}
