package br.com.brasilprev.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasilprev.exceptions.CustomerNotFoundException;
import br.com.brasilprev.model.Customer;
import br.com.brasilprev.rest.dto.CustomerResponse;
import br.com.brasilprev.rest.dto.mappers.CustomerMapper;
import br.com.brasilprev.service.facades.CustomerFacade;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CustomerController {
	protected final @NonNull CustomerFacade customerFacade;
	
	private CustomerMapper customerMapper = CustomerMapper.INSTANCE;
	
	@ApiOperation(value = "Get sigle customer", notes = "Retrieves a single customer represented by your identification")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Customer found")
		,@ApiResponse(code = 404, message = "When not found")
	})
	@GetMapping("{id}")
	public CustomerResponse doGetCustomer(@ApiParam(value = "Customer identification",
													type = "java.lang.Long",
													example = "1",
													required = true) @PathVariable("id") Long customerId) {
		Optional<Customer> customer = this.customerFacade.getById(customerId);
		
		if(!customer.isPresent()) {
			throw new CustomerNotFoundException();
		}
		
		CustomerResponse customerResponse = this.customerMapper.customerEntityToCustomerResponse(customer.get());
		customerResponse.add(linkTo(methodOn(CustomerController.class).doGetCustomer(customerResponse.getId())).withSelfRel());
		
		return customerResponse;
	}
}
