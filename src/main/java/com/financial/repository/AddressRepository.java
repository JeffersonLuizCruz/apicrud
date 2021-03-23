package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

}
