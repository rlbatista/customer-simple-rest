package br.com.brasilprev.facade;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.brasilprev.exceptions.CustomerAlreadyRegisteredException;
import br.com.brasilprev.model.Customer;

public class CustomerUpdateTests extends AbstractCustomerTests {

	@Test
	void whenUpdateCustomerWithValidValue_ThenOK() {
		String newName = "UPDATED NAME";
		Customer toUpdate = Customer.builder().name(newName).build();
		Customer updateCustomer = super.customerFacade.update(CUSTOMER_ID_ONE, toUpdate);
		
		Assertions.assertEquals(CUSTOMER_ID_ONE, updateCustomer.getId());
		Customer updatedCustomer = super.customerFacade.getById(CUSTOMER_ID_ONE).get();
		Assertions.assertEquals(newName, updatedCustomer.getName());
	}
	
	@Test
	void whenUpdateNullCustomer_ThenThrowsNPE() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			this.customerFacade.update(CUSTOMER_ID_ONE, null);
		});
		
		Assertions.assertThrows(NullPointerException.class, () -> {
			this.customerFacade.update(null, Customer.builder().build());
		});
	}
	
	@Test
	void whenUpdateCustomerWithIvalidData_ThenThrowsException() {
		Customer invalidCustomerData = super.createInvalidCustomer();
		Customer toUpdate =
			Customer
				.builder()
					.name(invalidCustomerData.getName())
					.address(invalidCustomerData.getAddress())
				.build();
		
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			this.customerFacade.update(CUSTOMER_ID_ONE, toUpdate);
		});
	}
	
	@Test
	void whenUpdateWithCPFAlreadyRegistered_ThenThrowsException() {
		Customer toUpdate = super.createValidCustomer();
		toUpdate.setCpf(CPF_REGISTERED_4);
		
		Assertions.assertThrows(CustomerAlreadyRegisteredException.class, () -> this.customerFacade.update(CUSTOMER_ID_ONE, toUpdate));
	}
}
