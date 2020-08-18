package br.com.brasilprev.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.brasilprev.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
