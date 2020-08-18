package br.com.brasilprev.service.facades;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brasilprev.model.Customer;
import br.com.brasilprev.service.CustomerReadService;
import br.com.brasilprev.service.CustomerSaveService;

@Component
public class CustomerFacade {
	
	private CustomerReadService customerReadService;
	private CustomerSaveService customerSaveService;

	@Autowired
	public CustomerFacade(CustomerReadService customerReadService, CustomerSaveService customerSaveService) {
		this.customerReadService = customerReadService;
		this.customerSaveService = customerSaveService;
	}

	public Optional<Customer> getById(Long id) {
		return this.customerReadService.getById(id);
	}

	public Customer save(Customer newCustomer) {
		return this.customerSaveService.save(newCustomer);
	}
}
