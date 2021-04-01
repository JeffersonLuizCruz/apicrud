package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entity.PaymentCard;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long>{

}
