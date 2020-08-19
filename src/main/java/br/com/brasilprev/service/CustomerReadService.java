package br.com.brasilprev.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
		Objects.requireNonNull(id, "Id can't be null");
		
		return this.customerRepo.findById(id);
	}

	public Optional<Customer> findByCpf(String cpf) {
		return this.customerRepo.findByCpf(cpf);
	}

	public List<Customer> getAll() {
		Iterable<Customer> itCustomers = this.customerRepo.findAll();
		return
			StreamSupport
				.stream(itCustomers.spliterator(), false)
				.sorted(Comparator.comparing(Customer::getName))
				.collect(Collectors.toList());
	}
}
