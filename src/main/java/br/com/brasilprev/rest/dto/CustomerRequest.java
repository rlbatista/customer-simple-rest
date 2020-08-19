package br.com.brasilprev.rest.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.caelum.stella.bean.validation.CPF;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerRequest {
	@ApiModelProperty(value = "Unformated CPF with 11 digits", name = "cpf", dataType = "String", example = "12345678900")
	@NotNull
	@CPF
	@Size(min = 11, max = 11)
	private String cpf;
	
	@ApiModelProperty(value = "Customer name", name = "name", dataType = "String", example = "Mikey Mouse")
	@NotBlank
	@Size(min = 2, max = 50)
	private String name;
	
	@ApiModelProperty(value = "Customer address", name = "address", dataType = "AddressRequest", example = "Check address for an example")
	@Valid
	private AddressRequest address;
}
