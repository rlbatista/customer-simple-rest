package br.com.brasilprev;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import br.com.brasilprev.config.DatabaseForTests;

@Import(DatabaseForTests.class)
@DataJpaTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class DesafioHexadataApplicationTests {

	@Test
	void contextLoads() {
	}

}
