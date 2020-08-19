package br.com.brasilprev.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasilprev.exceptions.CustomerNotFoundException;
import br.com.brasilprev.model.Customer;
import br.com.brasilprev.rest.dto.CustomerRequest;
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
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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

	@ApiOperation(value = "List all customers", notes = "Gel all registered customers with their individual links")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Customers found")
	})
	@GetMapping
	public List<CustomerResponse> doGetAllCustomers() {
		List<Customer> customers = this.customerFacade.getAll();
		List<CustomerResponse> responseCustomers = customers.stream().map(this.customerMapper::customerEntityToCustomerResponse).collect(Collectors.toList());
		responseCustomers.forEach(rc -> rc.add(linkTo(methodOn(CustomerController.class).doGetCustomer(rc.getId())).withSelfRel()));
		return responseCustomers;
	}
	
	@ApiOperation(value = "Creates a customer", notes = "Creates a new customer with informed data")
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Customer created")
		,@ApiResponse(code = 400, message = "When there are validations errors")
		,@ApiResponse(code = 409, message = "When customer being saved already exists")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerResponse doSaveCustomer(@ApiParam(	value = "Customer data",
														type = "br.com.brasilprev.rest.dto.CustomerRequest",
														required = true) @Valid @RequestBody CustomerRequest newCustomer) {
		Customer toSave = this.customerMapper.customerRequestToCustomerEntity(newCustomer);
		Customer saved = this.customerFacade.save(toSave);
		CustomerResponse toReturn = this.customerMapper.customerEntityToCustomerResponse(saved);
		toReturn.add(linkTo(methodOn(CustomerController.class).doGetCustomer(toReturn.getId())).withSelfRel());
		return toReturn;
	}
	
	@ApiOperation(value = "Updates a customer", notes = "Updates a customer with informed data")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Customer updated")
		,@ApiResponse(code = 400, message = "When there are validations errors or customer not exists")
		,@ApiResponse(code = 409, message = "When CPF customer belongs to another customer")
	})
	@PutMapping("{id}")
	public CustomerResponse doUpdateCustomer(	@ApiParam(	value = "Customer identification"
															,type = "java.lang.Long"
															,example = "1"
															,required = true)
	                                         	@PathVariable("id")
												Long id
												
												,@ApiParam(	value = "Customer data"
															,type = "br.com.brasilprev.rest.dto.CustomerRequest"
															,required = true)
												@RequestBody
												CustomerRequest customerData) {
		
		Customer updateMe = this.customerMapper.customerRequestToCustomerEntity(customerData);
		Customer updated = this.customerFacade.update(id, updateMe);
		
		CustomerResponse toReturn = this.customerMapper.customerEntityToCustomerResponse(updated);
		toReturn.add(linkTo(methodOn(CustomerController.class).doGetCustomer(toReturn.getId())).withSelfRel());
		return toReturn;
	}
}
