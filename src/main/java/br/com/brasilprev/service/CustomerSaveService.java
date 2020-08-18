package br.com.brasilprev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.dao.CustomerRepository;
import br.com.brasilprev.model.Customer;

@Service
public class CustomerSaveService {

	private CustomerRepository customerRepo;
	
	@Autowired
	public CustomerSaveService(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}
	
	@Transactional
	public Customer save(Customer newCustomer) {
		return this.customerRepo.save(newCustomer);
	}
}
