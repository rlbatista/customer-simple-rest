package br.com.brasilprev.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.brasilprev.model.Customer;
import lombok.Getter;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class CustomerAlreadyRegisteredException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	@Getter
	private final Customer customer;
	
	public CustomerAlreadyRegisteredException(Customer customer) {
		super(String.format("Customer CPF - %s - alread registered", customer.getCpf()));
		this.customer = customer;
	}
	
	public static void throwInstance(Customer customer) {
		throw new CustomerAlreadyRegisteredException(customer);
	}
}
