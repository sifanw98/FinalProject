package com.chuwa.learn.account_service.repository;

import com.chuwa.learn.account_service.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    List<PaymentMethod> findByType(PaymentMethod.PaymentType type);

//    PaymentMethod findByDefaultPaymentTrue();
}

