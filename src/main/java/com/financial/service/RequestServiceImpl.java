package com.financial.service;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financial.entity.Request;
import com.financial.entity.enums.StagePayment;
import com.financial.repository.RequestRepository;
import com.financial.repository.reposervice.RequestService;
import com.financial.service.exception.NotFoundException;

@Service
public class RequestServiceImpl implements RequestService{

	@Autowired private RequestRepository requestRepository;
	
	@Override
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		result.orElseThrow(() -> new NotFoundException("Não existe usuário com id " + id + ", Tipo: " + Request.class.getName()));
		return result.get();
	}

	@Override
	public Request save(Request request) {
		request.setId(null);
		request.setInstant(OffsetDateTime.now());
		// Iniciar com estado do pedido pendente.
		request.getPayment().setStage(StagePayment.PENDING);
		// O pedido precisa conhecer o pagamento
		request.getPayment().setRequest(request);
		return null;
	}

}
