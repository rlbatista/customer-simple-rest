package br.com.brasilprev;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlConfig.TransactionMode;
import org.springframework.test.context.jdbc.SqlGroup;

import br.com.brasilprev.model.Customer;
import br.com.brasilprev.model.embeddables.Address;
import br.com.brasilprev.service.facades.CustomerFacade;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SqlGroup({
	@Sql(scripts = "classpath:schema-test.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED)),
	@Sql("classpath:data-test.sql") })
class DesafioHexadataApplicationTests {

	private static final long SEQUENCE_START_VALUE = 1000L;
	private static final Long CUSTOMER_ID_ONE = 1L;
	private static final Long CUSTOMER_ID_NOT_EXISTS = -1L;
	@Autowired
	private CustomerFacade customerFacade;

	@Test
	void whenGetCustomerByIdAndExists_ThenOk() {
		Customer customer = this.customerFacade.getById(CUSTOMER_ID_ONE).orElseGet(null);
		Assertions.assertEquals("12345678901", customer.getCpf(), "Customer was not found");
	}

	@Test
	void whenGetCustomerByIdAndNotExists_ThenIsEmpty() {
		Optional<Customer> optCustomer = this.customerFacade.getById(CUSTOMER_ID_NOT_EXISTS);
		Assertions.assertFalse(optCustomer.isPresent(), "Was a negative ID found ???");
	}

	@Test
	void whenSaveValidCustomer_ThenOk() {
		Customer newCustomer = this.customerFacade.save(this.createCustomer());

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
	void whenSaveInvalidCustomer_ThenThrowsException() {
		Customer invalidCustomer = this.createCustomer();
		invalidCustomer.setCpf("123456789012");

		Assertions.assertThrows(Exception.class, () -> {
			this.customerFacade.save(invalidCustomer);
		}, "Method can saves invalid customer");
	}

	@Test
	void whenSaveCustomerWithFormattedCPFButValid_RemoveCharsThenOk() {
		Customer newCustomer = this.createCustomer();
		newCustomer.setCpf("603.417.400-71");
		newCustomer = this.customerFacade.save(newCustomer);
		Assertions.assertNotNull(newCustomer.getId(), "New customer wasn't save");

		Customer savedCustomer = this.customerFacade.getById(newCustomer.getId()).get();
		Assertions.assertEquals("60341740071", savedCustomer.getCpf(), "New customer with formatted CPF wasn't save");
	}

	// @formatter:off
	private Customer createCustomer() {
		return
			Customer
				.builder()
					.cpf("14631451017")
					.name("New Customer")
					.address(Address
					         .builder()
					         	.street("R Andradas")
					         	.number("234")
					         	.neighborhood("Vl Madalena")
					         	.city("Sao Paulo")
					         	.state("SP")
					         .build())
				.build();
	}
	// @formatter:on
}
