package com.chuwa.learn.account_service.repository;

import com.chuwa.learn.account_service.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByAddressType(Address.AddressType addressType);
}
