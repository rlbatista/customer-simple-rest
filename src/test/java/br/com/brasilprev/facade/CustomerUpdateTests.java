package br.com.brasilprev.facade;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.brasilprev.model.Customer;

public class CustomerUpdateTests extends AbstractCustomerTests {

	@Test
	void whenUpdateCustomerWithValidValue_ThenOK() {
		String newName = "UPDATED NAME";
		Customer toUpdate = Customer.builder().id(CUSTOMER_ID_ONE).name(newName).build();
		Customer updateCustomer = super.customerFacade.update(toUpdate);
		
		Assertions.assertEquals(CUSTOMER_ID_ONE, updateCustomer.getId());
		Customer updatedCustomer = super.customerFacade.getById(CUSTOMER_ID_ONE).get();
		Assertions.assertEquals(newName, updatedCustomer.getName());
	}
	
	@Test
	void whenUpdateNullCustomer_ThenThrowsNPE() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			this.customerFacade.update(null);
		});
	}
	
	@Test
	void whenUpdateCustomerWithIvalidData_ThenThrowsException() {
		Customer invalidCustomerData = super.createInvalidCustomer();
		Customer toUpdate =
			Customer
				.builder()
					.id(CUSTOMER_ID_ONE)
					
					.name(invalidCustomerData.getName())
					.address(invalidCustomerData.getAddress())
				.build();
		
		Assertions.assertThrows(ConstraintViolationException.class, () -> {
			this.customerFacade.update(toUpdate);
		});
	}
}