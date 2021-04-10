package com.financial.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.financial.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{
	
	@Transactional(readOnly=true)
	public List<State> findAllByOrderByName();

}
