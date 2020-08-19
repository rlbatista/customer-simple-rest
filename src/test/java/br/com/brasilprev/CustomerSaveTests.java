package br.com.brasilprev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.brasilprev.exceptions.CustomerAlreadyRegisteredException;
import br.com.brasilprev.model.Customer;

public class CustomerSaveTests extends AbstractCustomerTests {
	@Test
	void whenSaveValidCustomer_ThenOk() {
		Customer newCustomer = super.customerFacade.save(super.createValidCustomer());

		Assertions.assertAll("customerProperties", () -> {
			Assertions.assertNotNull(newCustomer.getId(), "New customer wasn't save");
			Assertions.assertEquals(SEQUENCE_START_VALUE, newCustomer.getId(), "Sequence is wrong");
			Assertions.assertEquals("14631451017", newCustomer.getCpf(), "CPF for new customer is wrong");
			Assertions.assertEquals("New Customer", newCustomer.getName(), "Name for new customer is wrong");
			Assertions.assertEquals("R Andradas", newCustomer.getAddress().getStreet(), "Street address for new customer is wrong");
			Assertions.assertEquals("234", newCustomer.getAddress().getNumber(), "Number address for new customer is wrong");
			Assertions.assertEquals("Vl Madalena", newCustomer.getAddress().getNeighborhood(), "Neighborhood address for new customer is wrong");
			Assertions.assertNull(newCustomer.getAddress().getComplement(), "Complement address must be null");
			Assertions.assertEquals("Sao Paulo", newCustomer.getAddress().getCity(), "City address for new customer is wrong");
			Assertions.assertEquals("SP", newCustomer.getAddress().getState(), "City address for new customer is wrong");
		});
	}

	@Test
	void whenSaveNullCustomer_ThenThrowsNPE() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			super.customerFacade.save(null);
		}, "Can method save null customer ???");
	}
	
	@Test
	void whenSaveRegisteredCustomer_ThenThrowSpecificException() {
		Customer registeredCustomer = super.customerFacade.save(super.createValidCustomer());
		registeredCustomer.setCpf(CPF_REGISTERED_ON_TEST_SQL_FILE);
		
		CustomerAlreadyRegisteredException exception =
			Assertions.assertThrows(CustomerAlreadyRegisteredException.class, () -> {
				super.customerFacade.save(registeredCustomer);
			}, "Shouldn't save duplicated CPF");
		
		Assertions.assertEquals("Fulano do teste", exception.getCustomer().getName(), "Is duplicated customer wrong ?");
	}
	
	@Test
	void whenSaveInvalidCustomer_ThenThrowsException() {
		Customer invalidCustomer = super.createValidCustomer();
		invalidCustomer.setCpf("123456789012");

		Assertions.assertThrows(Exception.class, () -> {
			super.customerFacade.save(invalidCustomer);
		}, "Method can saves invalid customer");
	}

	@Test
	void whenSaveCustomerWithFormattedCPFButValid_RemoveCharsThenOk() {
		Customer newCustomer = super.createValidCustomer();
		newCustomer.setCpf("603.417.400-71");
		newCustomer = super.customerFacade.save(newCustomer);
		Assertions.assertNotNull(newCustomer.getId(), "New customer wasn't save");

		Customer savedCustomer = super.customerFacade.getById(newCustomer.getId()).get();
		Assertions.assertEquals("60341740071", savedCustomer.getCpf(), "New customer with formatted CPF wasn't save");
	}

}
