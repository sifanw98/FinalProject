package com.chuwa.learn.account_service.converter;

import com.chuwa.learn.account_service.dto.account.AddressDTO;
import com.chuwa.learn.account_service.model.Address;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AddressConverter {
    public AddressDTO toAddressDTO(Address address) {
        if (address == null) {
            return null;
        }

        return AddressDTO.builder()
                .id(address.getId())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .postalCode(address.getPostalCode())
                .country(address.getCountry())
                .addressType(address.getAddressType())
                .build();
    }

    public Address toAddress(AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        return Address.builder()
                .id(dto.getId())
                .addressLine1(dto.getAddressLine1())
                .addressLine2(dto.getAddressLine2())
                .city(dto.getCity())
                .state(dto.getState())
                .postalCode(dto.getPostalCode())
                .country(dto.getCountry())
                .addressType(dto.getAddressType())
                .build();
    }

    /**
     * Convert collection of AddressDTO to Set of Address entities.
     */
    public Set<Address> toAddressSet(Set<AddressDTO> addressDTOs) {
        if (addressDTOs == null) {
            return new HashSet<>();
        }

        return addressDTOs.stream()
                .map(this::toAddress)
                .collect(Collectors.toSet());
    }
}
