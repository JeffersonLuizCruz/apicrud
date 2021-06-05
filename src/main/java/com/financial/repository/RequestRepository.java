package com.financial.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.financial.entity.Customer;
import com.financial.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	
	@Transactional(readOnly=true)
	Page<Request> findByCustomer(Customer customer, Pageable pageRequest);
	

}
