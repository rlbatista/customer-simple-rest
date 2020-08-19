package br.com.brasilprev.facade;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.brasilprev.model.Customer;

public class CustomerReadTests extends AbstractCustomerTests {
	
	@Test
	void whenGetCustomerByIdAndExists_ThenOk() {
		Customer customer = super.customerFacade.getById(CUSTOMER_ID_ONE).orElseGet(null);
		Assertions.assertEquals(CPF_REGISTERED_1, customer.getCpf(), "Customer was not found");
	}

	@Test
	void whenGetCustomerByIdAndNotExists_ThenIsEmpty() {
		Optional<Customer> optCustomer = super.customerFacade.getById(CUSTOMER_ID_NOT_EXISTS);
		Assertions.assertFalse(optCustomer.isPresent(), "Was a negative ID found ???");
	}
	
	@Test
	void whenGetCustomerWithNullId_ThenThrowsNPE() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			super.customerFacade.getById(null);
		}, "How can be possible ? Null id on db");
	}
	
	@Test
	void whenGetAllCustomers_ThenOK() {
		List<Customer> customers = super.customerFacade.getAll();
		Assertions.assertEquals(5, customers.size());
	}
}
