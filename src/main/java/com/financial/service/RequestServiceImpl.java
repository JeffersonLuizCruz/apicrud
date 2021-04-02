package com.financial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.financial.entity.Request;
import com.financial.repository.RequestRepository;
import com.financial.repository.reposervice.RequestService;
import com.financial.service.exception.NotFoundException;

public class RequestServiceImpl implements RequestService{

	@Autowired private RequestRepository requestRepository;
	
	@Override
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		result.orElseThrow(() -> new NotFoundException("Não existe usuário com id " + id + ", Tipo: " + Request.class.getName()));
		return result.get();
	}

}
