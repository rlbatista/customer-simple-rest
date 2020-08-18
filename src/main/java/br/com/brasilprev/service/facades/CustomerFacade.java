package br.com.brasilprev.service.facades;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brasilprev.model.Customer;
import br.com.brasilprev.service.CustomerReadService;

@Component
public class CustomerFacade {
	
	private CustomerReadService customerReadService;

	@Autowired
	public CustomerFacade(CustomerReadService customerReadService) {
		this.customerReadService = customerReadService;
	}

	public Optional<Customer> getById(Long id) {
		return this.customerReadService.getById(id);
	}
}
