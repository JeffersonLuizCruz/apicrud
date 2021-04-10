package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{
	

}
