package br.com.brasilprev.service.facades;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brasilprev.model.Customer;
import br.com.brasilprev.service.CustomerReadService;
import br.com.brasilprev.service.CustomerSaveService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CustomerFacade {
	
	private final @NonNull CustomerReadService customerReadService;
	private final @NonNull CustomerSaveService customerSaveService;

	public Optional<Customer> getById(Long id) {
		return this.customerReadService.getById(id);
	}

	public Customer save(Customer newCustomer) {
		return this.customerSaveService.save(newCustomer);
	}
}
