package br.com.brasilprev.service;

import static java.lang.String.format;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.dao.CustomerRepository;
import br.com.brasilprev.exceptions.CustomerNotFoundException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CustomerDeleteService {
	
	private final @NonNull CustomerRepository customerRepo;
	
	@Transactional
	public void deleteById(Long customerIdToDelete) {
		try {
			this.customerRepo.deleteById(customerIdToDelete);;
			
		} catch(EmptyResultDataAccessException e) {
			throw new CustomerNotFoundException(format("Customer ID %d not exists", customerIdToDelete));
		}
	}
}
