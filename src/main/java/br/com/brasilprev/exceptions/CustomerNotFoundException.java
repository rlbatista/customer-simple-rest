package br.com.brasilprev.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public CustomerNotFoundException() {
		super("Customer not found");
	}
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
}
