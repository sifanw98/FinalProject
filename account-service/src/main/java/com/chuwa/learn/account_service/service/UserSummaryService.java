package com.chuwa.learn.account_service.service;

import com.chuwa.learn.account_service.dto.UserSummaryDTO;

public interface UserSummaryService {
    UserSummaryDTO getUserSummary(Long userId);
}