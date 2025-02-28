package com.chuwa.learn.account_service.converter;

import com.chuwa.learn.account_service.dto.account.AddressDTO;
import com.chuwa.learn.account_service.dto.account.PaymentMethodDTO;
import com.chuwa.learn.account_service.dto.account.UserDTO;
import com.chuwa.learn.account_service.model.Address;
import com.chuwa.learn.account_service.model.PaymentMethod;
import com.chuwa.learn.account_service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    private final AddressConverter addressConverter;
    private final PaymentMethodConverter paymentMethodConverter;

    @Autowired
    public UserConverter(AddressConverter addressConverter, PaymentMethodConverter paymentMethodConverter) {
        this.addressConverter = addressConverter;
        this.paymentMethodConverter = paymentMethodConverter;
    }

    public UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }

        Set<Address> addresses = user.getAddresses();
        Set<PaymentMethod> paymentMethods = user.getPaymentMethods();

        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .addresses(addresses != null ?
                        addresses.stream()
                                .map(addressConverter::toAddressDTO)
                                .collect(Collectors.toSet()) :
                        null)
                .paymentMethods(paymentMethods != null ?
                        paymentMethods.stream()
                                .map(paymentMethodConverter::toPaymentMethodDTO)
                                .collect(Collectors.toSet()) :
                        null)
                .roles(user.getRoles())
                .build();
    }

    public User toUser(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .addresses(dto.getAddresses() != null ?
                        dto.getAddresses().stream()
                                .map(addressConverter::toAddress)
                                .collect(Collectors.toSet()) :
                        null)
                .paymentMethods(dto.getPaymentMethods() != null ?
                        dto.getPaymentMethods().stream()
                                .map(paymentMethodConverter::toPaymentMethod)
                                .collect(Collectors.toSet()) :
                        null)
                .roles(dto.getRoles())
                .build();
    }

    public void updateUserFromDTO(UserDTO dto, User user) {
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }

        if (dto.getPassword() != null) {
            user.setPassword(dto.getPassword());
        }

        if (dto.getRoles() != null) {
            user.setRoles(dto.getRoles());
        }
    }

}
