package br.com.brasilprev;

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
import br.com.brasilprev.service.facades.CustomerFacade;

@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@SqlGroup({
	@Sql(scripts = "classpath:schema-test.sql", config = @SqlConfig(transactionMode = TransactionMode.ISOLATED)),
	@Sql("classpath:data-test.sql") })
class DesafioHexadataApplicationTests {

	@Autowired
	private CustomerFacade customerFacade;

	@Test
	void whenGetCustomerByIdAndExists_ThenOk() {
		Customer customer = this.customerFacade.getById(1L).orElseGet(null);
		Assertions.assertEquals("12345678901", customer.getCpf());
	}
}
