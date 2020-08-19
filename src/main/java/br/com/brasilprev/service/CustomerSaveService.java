package br.com.brasilprev.service;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import br.com.brasilprev.dao.CustomerRepository;
import br.com.brasilprev.model.Customer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
@Validated
public class CustomerSaveService {

	private final @NonNull CustomerRepository customerRepo;
	
	@Transactional
	public Customer save(@Valid Customer newCustomer) {
		Objects.requireNonNull(newCustomer, "New customers can't be null");
		return this.customerRepo.save(newCustomer);
	}
}
