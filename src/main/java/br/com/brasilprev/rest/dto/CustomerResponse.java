package br.com.brasilprev.rest.dto;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerResponse extends RepresentationModel<CustomerResponse> {
	@ApiModelProperty(value = "Customer identifier", name = "id", dataType = "Long", example = "1")
	private Long id;
	
	@ApiModelProperty(value = "Unformated CPF with 11 digits", name = "cpf", dataType = "String", example = "12345678900")
	private String cpf;
	
	@ApiModelProperty(value = "Customer name", name = "name", dataType = "String", example = "Mickey Mouse")
	private String name;
	
	@ApiModelProperty(value = "Customer address", name = "address", dataType = "AddressResponse", example = "Check address for an example")
	private AddressResponse address;
}
