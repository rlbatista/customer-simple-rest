package br.com.brasilprev.rest.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressRequest {
	@ApiModelProperty(value = "Street name", name = "street", dataType = "String", example = "St Francisco Glicerio")
	@Size(min = 2, max = 60)
	@NotBlank
	private String street;
	
	@ApiModelProperty(value = "Number of buildind", name = "number", dataType = "String", example = "23A")
	@Size(min = 1, max = 10)
	@NotBlank
	private String number;
	
	@ApiModelProperty(value = "Complement address (optional)", name = "complement", dataType = "String", example = "Apt 5")
	@Size(max = 50)
	private String complement;
	
	@ApiModelProperty(value = "Neighboorhood name", name = "neighborhood", dataType = "String", example = "Centro")
	@Size(min = 2, max = 30)
	@NotBlank
	private String neighborhood;
	
	@ApiModelProperty(value = "City name", name = "city", dataType = "String", example = "Campinas")
	@Size(min = 2, max = 30)
	@NotBlank
	private String city;
	
	@ApiModelProperty(value = "State code with 2 digits", name = "state", dataType = "String", example = "SP")
	@Size(min = 2, max = 2)
	@NotBlank
	private String state;
}
