package br.com.brasilprev.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import br.com.brasilprev.model.embeddables.Address;
import br.com.caelum.stella.bean.validation.CPF;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = { "cpf" }, name = "uk_customer_cpf"))
@DynamicUpdate(true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
	@GenericGenerator(	name = "sq_customer",
						strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
						parameters = { @Parameter(name = "sequence_name", value = "sq_customer"),
							@Parameter(name = "initial_value", value = "1000") })
	@GeneratedValue(generator = "sq_customer")
	@Id
	private Long id;

	@NotNull
	@CPF
	@Size(min = 11, max = 11)
	@EqualsAndHashCode.Include
	private String cpf;

	@Column
	@NotBlank
	@Size(min = 2, max = 50)
	private String name;

	@Embedded
	@Valid
	private Address address;
}
