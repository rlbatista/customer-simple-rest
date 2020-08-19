package br.com.brasilprev;

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
abstract class AbstractCustomerTests {

	protected static final Long SEQUENCE_START_VALUE = 1000L;
	protected static final Long CUSTOMER_ID_ONE = 1L;
	protected static final Long CUSTOMER_ID_NOT_EXISTS = -1L;
	protected static final String CPF_REGISTERED_ON_TEST_SQL_FILE = "20455988021";
	
	@Autowired
	protected CustomerFacade customerFacade;

	// @formatter:off
	protected Customer createValidCustomer() {
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
