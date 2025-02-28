package com.chuwa.learn.account_service.config;

import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@Slf4j
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.getCredentials() instanceof String) {
                String token = (String) authentication.getCredentials();
                requestTemplate.header("Authorization", "Bearer " + token);
            }
        };
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            log.error("Feign client error: {} - {}", response.status(), response.reason());

            if (response.status() >= 400 && response.status() <= 499) {
                return new RuntimeException("Client error when calling " + methodKey + ": " + response.reason());
            }

            if (response.status() >= 500 && response.status() <= 599) {
                return new RuntimeException("Server error when calling " + methodKey + ": " + response.reason());
            }

            return new RuntimeException("Unexpected error calling " + methodKey + ": " + response.reason());
        };
    }
}
