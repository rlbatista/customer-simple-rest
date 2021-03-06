package br.com.brasilprev.service.facades;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brasilprev.exceptions.CustomerAlreadyRegisteredException;
import br.com.brasilprev.model.Customer;
import br.com.brasilprev.service.CustomerDeleteService;
import br.com.brasilprev.service.CustomerReadService;
import br.com.brasilprev.service.CustomerSaveService;
import br.com.brasilprev.service.CustomerUpdateService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CustomerFacade {
	
	private final @NonNull CustomerReadService customerReadService;
	private final @NonNull CustomerSaveService customerSaveService;
	private final @NonNull CustomerUpdateService customerUpdateService;
	private final @NonNull CustomerDeleteService customerDeleteService;

	public Optional<Customer> getById(Long id) {
		return this.customerReadService.getById(id);
	}

	public Customer save(Customer newCustomer) {
		if(newCustomer.getCpf() != null) {
			newCustomer.setCpf(newCustomer.getCpf().replaceAll("\\D", ""));
			this.customerReadService.findByCpf(newCustomer.getCpf()).ifPresent(CustomerAlreadyRegisteredException::throwInstance);
		}
		return this.customerSaveService.save(newCustomer);
	}

	public Customer update(Long idCustomerToUpdate, Customer customerData) {
		return this.customerUpdateService.update(idCustomerToUpdate, customerData);
	}

	public void deleteCustomerById(Long customerIdToDelete) {
		Objects.requireNonNull(customerIdToDelete, "Customer ID is mandatory for delete");
		
		this.customerDeleteService.deleteById(customerIdToDelete);
	}

	public List<Customer> getAll() {
		return this.customerReadService.getAll();
	}
}
