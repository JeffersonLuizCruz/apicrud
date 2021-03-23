package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
