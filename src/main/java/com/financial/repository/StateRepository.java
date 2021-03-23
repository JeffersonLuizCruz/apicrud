package com.financial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.financial.entity.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

}
