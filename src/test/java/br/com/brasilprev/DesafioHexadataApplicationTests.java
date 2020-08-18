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
		Assertions.assertNotNull(newCustomer.getId(), "New customer wasn't save");
		Assertions.assertEquals(SEQUENCE_START_VALUE, newCustomer.getId(), "Sequence is wrong");
	}
	
	private Customer createCustomer() {
		return
			Customer
				.builder()
					.cpf("12345678998")
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
}
