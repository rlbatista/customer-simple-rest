package br.com.brasilprev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.dao.CustomerRepository;
import br.com.brasilprev.model.Customer;

@Service
public class CustomerReadService {
	
	private CustomerRepository customerRepo;

	@Autowired
	public CustomerReadService(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}
	
	@Transactional(readOnly = true)
	public Optional<Customer> getById(Long id) {
		return this.customerRepo.findById(id);
	}
}
