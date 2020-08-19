package br.com.brasilprev.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.brasilprev.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	Optional<Customer> findByCpf(String cpf);
	boolean existsByCpfAndIdNot(String cnpj, Long id);
}
