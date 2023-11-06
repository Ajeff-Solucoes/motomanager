package br.com.jeffersondev.motormanager.dto.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jeffersondev.motormanager.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O campo nome não pode ser nulo ou vazio!")
	@Size(min = 2, max = 100, message = "O campo nome deve ter entre 2 e 100 caracteres!")
	private String nome;

	@NotBlank(message = "O campo med não pode ser nulo ou vazio!")
	@Size(min = 3, max = 3, message = "O campo nome deve ter 3 caracteres!")
	private String med;
	
	@NotNull(message = "O campo valor não pode ser nulo ou vazio!")
	@DecimalMin(value = "0", message = "O valor não pode ser negativo")
	private BigDecimal valor;

	
	public Produto fromDtoToEntity() {
		return new Produto().create(this.nome.trim(), this.med.trim(), this.valor);
	}
}
