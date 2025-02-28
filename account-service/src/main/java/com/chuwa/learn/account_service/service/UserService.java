package com.chuwa.learn.account_service.service;

import com.chuwa.learn.account_service.dto.account.UserDTO;

import java.util.Optional;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    Optional<UserDTO> getUserById(Long id);

    UserDTO updateUser(Long id, UserDTO userDTO);

    void deleteUser(Long id);

}
