package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entity.PaymentSlip;

@Repository
public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long>{

}
