package br.com.brasilprev.rest.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.brasilprev.model.embeddables.Address;
import br.com.brasilprev.rest.dto.AddressRequest;
import br.com.brasilprev.rest.dto.AddressResponse;

@Mapper
public interface AddressMapper {
	public static final AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
	
	AddressRequest addressEntityToAddressRequest(Address entity);
	AddressResponse addressToAddressResponse(Address entity);
	
	Address addressRequestToAddressEntity(AddressRequest addressRequest);
	Address addressResponseToAddressEntity(AddressResponse addressResponse);
}
