package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entity.CardPayment;

@Repository
public interface CardPaymentRepository extends JpaRepository<CardPayment, Long>{

}
