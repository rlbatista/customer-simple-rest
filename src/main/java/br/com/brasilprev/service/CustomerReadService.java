package br.com.brasilprev.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.dao.CustomerRepository;
import br.com.brasilprev.model.Customer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CustomerReadService {
	
	private final @NonNull CustomerRepository customerRepo;

	@Transactional(readOnly = true)
	public Optional<Customer> getById(Long id) {
		return this.customerRepo.findById(id);
	}
}
