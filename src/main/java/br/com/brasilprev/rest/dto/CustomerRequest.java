package br.com.brasilprev.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerRequest {
	@ApiModelProperty(value = "Unformated CPF with 11 digits", name = "cpf", dataType = "String", example = "12345678900")
	private String cpf;
	
	@ApiModelProperty(value = "Customer name", name = "name", dataType = "String", example = "Mikey Mouse")
	private String name;
	
	@ApiModelProperty(value = "Customer address", name = "address", dataType = "AddressRequest", example = "Check address for an example")
	private AddressRequest address;
}
