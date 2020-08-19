package br.com.brasilprev.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.dao.CustomerRepository;
import br.com.brasilprev.exceptions.CustomerAlreadyRegisteredException;
import br.com.brasilprev.model.Customer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CustomerSaveService {

	private final @NonNull CustomerRepository customerRepo;
	
	@Transactional
	public Customer save(Customer newCustomer) {
		Objects.requireNonNull(newCustomer, "New customers can't be null");
		
		if(newCustomer.getCpf() != null) {
			newCustomer.setCpf(newCustomer.getCpf().replaceAll("\\D", ""));
			this.customerRepo.findByCpf(newCustomer.getCpf()).ifPresent(CustomerAlreadyRegisteredException::throwInstance);
		}
		
		return this.customerRepo.save(newCustomer);
	}
}
