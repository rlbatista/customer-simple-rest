package br.com.brasilprev.rest.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressRequest {
	@ApiModelProperty(value = "Street name", name = "street", dataType = "String", example = "St Francisco Glicerio")
	private String street;
	
	@ApiModelProperty(value = "Number of buildind", name = "number", dataType = "String", example = "23A")
	private String number;
	
	@ApiModelProperty(value = "Complement address (optional)", name = "complement", dataType = "String", example = "Apt 5")
	private String complement;
	
	@ApiModelProperty(value = "Neighboorhood name", name = "neighborhood", dataType = "String", example = "Centro")
	private String neighborhood;
	
	@ApiModelProperty(value = "City name", name = "city", dataType = "String", example = "Campinas")
	private String city;
	
	@ApiModelProperty(value = "State code with 2 digits", name = "state", dataType = "String", example = "SP")
	private String state;
}
