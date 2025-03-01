package com.chuwa.learn.account_service.controller;

import com.chuwa.learn.account_service.dto.UserSummaryDTO;
import com.chuwa.learn.account_service.exception.ResourceNotFoundException;
import com.chuwa.learn.account_service.service.UserSummaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts/summary")
@Tag(name = "User Summary", description = "User Summary API with integrated services data")
@RequiredArgsConstructor
@Slf4j
public class UserSummaryController {

    private final UserSummaryService userSummaryService;

    @GetMapping("/{userId}")
    @Operation(summary = "Get user summary", description = "Get user summary with orders and payments")
    public ResponseEntity<UserSummaryDTO> getUserSummary(@PathVariable Long userId) {
        log.info("Fetching summary for user with id: {}", userId);

        UserSummaryDTO summary = userSummaryService.getUserSummary(userId);

        if (summary == null) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        return ResponseEntity.ok(summary);
    }
}
