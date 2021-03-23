package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entity.ItemRequest;

@Repository
public interface ItemRequestRepository extends JpaRepository<ItemRequest, Long>{

}
