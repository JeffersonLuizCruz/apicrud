package com.financial.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financial.entity.ItemRequest;
import com.financial.entity.PaymentSlip;
import com.financial.entity.Request;
import com.financial.entity.enums.StagePayment;
import com.financial.repository.ItemRequestRepository;
import com.financial.repository.PaymentRepository;
import com.financial.repository.RequestRepository;
import com.financial.repository.reposervice.RequestService;
import com.financial.service.exception.NotFoundException;

@Service
public class RequestServiceImpl implements RequestService{

	@Autowired private RequestRepository requestRepository;
	@Autowired private PaymentSlipService paymentSlipService;
	@Autowired private CustomerServiceImpl customerService;
	@Autowired private PaymentRepository paymentRepository;
	@Autowired private ItemRequestRepository itemRepository;
	@Autowired private ProductServiceImpl productService;
	
	
	@Override
	public Request getById(Long id) {
		Optional<Request> result = requestRepository.findById(id);
		result.orElseThrow(() -> new NotFoundException("Não existe usuário com id " + id + ", Tipo: " + Request.class.getName()));
		return result.get();
	}

	@Transactional
	@Override
	public Request save(Request request) {
		request.setId(null);
		request.setInstant(OffsetDateTime.now());
		// Cardinalidade: 1 request contém no minimo 1 customer.
		request.setCustomer(customerService.getById(request.getCustomer().getId()));
		// Iniciar com estado do pedido pendente.
		request.getPayment().setStage(StagePayment.PENDING);
		// O pedido precisa conhecer o pagamento em relaçionamento OneToOne
		request.getPayment().setRequest(request);
		
		if (request.getPayment() instanceof PaymentSlip) {
			PaymentSlip paymentSlip = (PaymentSlip) request.getPayment();
			paymentSlipService.requestPaymentSlip(paymentSlip, request.getInstant());
		}
		
		request = requestRepository.save(request); // Relacionamento Bidirecional
		paymentRepository.save(request.getPayment()); // Relacionamento Bidirecional
		
		for(ItemRequest ir : request.getItems()) {
			ir.setDiscount(new BigDecimal("0.0"));
			ir.setProduct(productService.getById(ir.getProduct().getId()));
			ir.setPrice(ir.getProduct().getPrice());
			ir.setRequest(request);
		}
		itemRepository.saveAll(request.getItems());
		System.out.println(request);
		return request;
	}

}
