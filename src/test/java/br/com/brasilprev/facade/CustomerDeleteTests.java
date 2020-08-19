package br.com.brasilprev.facade;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.brasilprev.exceptions.CustomerNotFoundException;
import br.com.brasilprev.model.Customer;

public class CustomerDeleteTests extends AbstractCustomerTests {
	
	@Test
	void whenDeleteExistingCustomer_ThenOK() throws Exception {
		super.customerFacade.deleteCustomerById(CUSTOMER_ID_ONE);
		Optional<Customer> deleteCustomer = super.customerFacade.getById(CUSTOMER_ID_ONE);
		Assertions.assertFalse(deleteCustomer.isPresent(), "System can't delete customer");
	}
	
	@Test
	void whenDeleteNonExistingCustomer_ThenThrowsException() {
		Assertions.assertThrows(CustomerNotFoundException.class, () -> {
			this.customerFacade.deleteCustomerById(CUSTOMER_ID_NOT_EXISTS);
		});
	}
}
