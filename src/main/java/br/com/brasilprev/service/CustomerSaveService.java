package br.com.brasilprev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.dao.CustomerRepository;
import br.com.brasilprev.model.Customer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CustomerSaveService {

	private final @NonNull CustomerRepository customerRepo;
	
	@Transactional
	public Customer save(Customer newCustomer) {
		return this.customerRepo.save(newCustomer);
	}
}
