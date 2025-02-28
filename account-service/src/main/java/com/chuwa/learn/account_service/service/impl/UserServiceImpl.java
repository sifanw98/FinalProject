package com.chuwa.learn.account_service.service.impl;

import com.chuwa.learn.account_service.converter.AddressConverter;
import com.chuwa.learn.account_service.converter.PaymentMethodConverter;
import com.chuwa.learn.account_service.converter.UserConverter;
import com.chuwa.learn.account_service.dto.account.UserDTO;
import com.chuwa.learn.account_service.exception.ResourceNotFoundException;
import com.chuwa.learn.account_service.model.User;
import com.chuwa.learn.account_service.repository.UserRepository;
import com.chuwa.learn.account_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.chuwa.learn.account_service.event.producer.AccountEventProducer;

import java.util.HashSet;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final AddressConverter addressConverter;
    private final PaymentMethodConverter paymentMethodConverter;
    private final AccountEventProducer eventProducer;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserConverter userConverter,
                           AddressConverter addressConverter,
                           PaymentMethodConverter paymentMethodConverter,
                           AccountEventProducer eventProducer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userConverter = userConverter;
        this.addressConverter = addressConverter;
        this.paymentMethodConverter = paymentMethodConverter;
        this.eventProducer = eventProducer;
    }


    @Transactional
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        log.info("Creating new user with email: {}", userDTO.getEmail());

        // Check if user already exists
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email is already in use!");
        }

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new RuntimeException("Username is already in use!");
        }

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPassword);

        // Create new user
        User user = userConverter.toUser(userDTO);

        // Set default role if not specified
        if (userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
            HashSet<String> roles = new HashSet<>();
            roles.add("ROLE_USER");
            user.setRoles(roles);
        } else {
            user.setRoles(userDTO.getRoles());
        }

        // Set addresses and payment methods using converter
        user.setAddresses(addressConverter.toAddressSet(userDTO.getAddresses()));
        user.setPaymentMethods(paymentMethodConverter.toPaymentMethodSet(userDTO.getPaymentMethods()));

        userRepository.save(user);
        eventProducer.publishUserRegistered(user);

        UserDTO savedUserDTO = userConverter.toUserDTO(user);
        savedUserDTO.setPassword(null);

        return savedUserDTO;
    }

    @Transactional
    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        log.info("Updating user with id: {}", id);

        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found with id: " + id));

        if (userDTO.getEmail() != null && !userDTO.getEmail().equals(user.getEmail())
                && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        if (userDTO.getUsername() != null && !userDTO.getUsername().equals(user.getUsername())
                && userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalArgumentException("Username already in use");
        }

        // Only update password if provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        } else {
            userDTO.setPassword(user.getPassword());
        }

        userConverter.updateUserFromDTO(userDTO, user);
        userRepository.save(user);

        UserDTO updatedUserDTO = userConverter.toUserDTO(user);
        updatedUserDTO.setPassword(null);

        return updatedUserDTO;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> getUserById(Long id) {
        log.info("Fetching user with id: {}", id);

        return userRepository.findById(id)
                .map(user -> {
                    UserDTO dto = userConverter.toUserDTO(user);
                    dto.setPassword(null);
                    return dto;
                    });
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }

        userRepository.deleteById(id);
    }
}