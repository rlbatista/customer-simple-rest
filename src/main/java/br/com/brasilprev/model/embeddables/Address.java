package br.com.brasilprev.model.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Address {
	@Column
	@Size(min = 2, max = 60)
	@NotBlank
	private String street;
	
	@Column
	@Size(min = 1, max = 10)
	@NotBlank
	private String number;
	
	@Column
	@Size(max = 50)
	private String complement;

	@Column
	@Size(min = 2, max = 30)
	@NotBlank
	private String neighborhood;
	
	@Column
	@Size(min = 2, max = 30)
	@NotBlank
	private String city;
	
	@Column(name = "state_code")
	@Size(min = 2, max = 2)
	@NotBlank
	private String state;
}
