package br.com.brasilprev.rest.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.brasilprev.model.Customer;
import br.com.brasilprev.rest.dto.CustomerRequest;
import br.com.brasilprev.rest.dto.CustomerResponse;

@Mapper(uses = {AddressMapper.class})
public interface CustomerMapper {
	public static final CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	CustomerRequest customerEntityToCustomerRequest(Customer entity);
	CustomerResponse customerEntityToCustomerResponse(Customer entity);
	
	Customer customerRequestToCustomerEntity(CustomerRequest customerRequest);
	Customer customerResponseToCustomerEntity(CustomerResponse customerResponse);
}
