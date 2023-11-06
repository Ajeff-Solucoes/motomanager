package br.com.jeffersondev.motormanager.dto.servico;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jeffersondev.motormanager.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicoRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O campo nome não pode ser nulo ou vazio!")
	@Size(min = 2, max = 100, message = "O campo nome deve ter entre 2 e 100 caracteres!")
	private String nome;
	
	@NotNull(message = "O campo valor não pode ser nulo ou vazio!")
	@DecimalMin(value = "0", message = "O valor não pode ser negativo")
	private BigDecimal valor;

	
	
	public Servico fromDtoToEntity() {
		return new Servico().create(this.nome.trim(), this.valor);
	}
}
