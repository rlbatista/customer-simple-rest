package br.com.brasilprev.service;

import java.beans.FeatureDescriptor;
import java.util.Objects;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.brasilprev.dao.CustomerRepository;
import br.com.brasilprev.exceptions.CustomerNotFoundException;
import br.com.brasilprev.model.Customer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CustomerUpdateService {
	private final @NonNull CustomerRepository customerRepo;
	
	@Transactional
	public Customer update(Customer existingCustomer) {
		Objects.requireNonNull(existingCustomer, "Customer to edit can't be null");
		Customer originalCustomer = this.customerRepo.findById(existingCustomer.getId()).orElseThrow(CustomerNotFoundException::new);
		BeanUtils.copyProperties(existingCustomer, originalCustomer, getNullPropertyNames(existingCustomer));
		return this.customerRepo.save(originalCustomer);
	}
	
	private static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
	    return
	    	Stream
	    		.of(wrappedSource.getPropertyDescriptors())
	            .map(FeatureDescriptor::getName)
	            .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
	            .toArray(String[]::new);
	}
}
