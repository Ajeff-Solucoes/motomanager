package br.com.jeffersondev.motormanager.dto.mecanico;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.jeffersondev.motormanager.entity.Mecanico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MecanicoRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O campo nome não pode ser nulo ou vazio!")
	@Size(min = 2, max = 100, message = "O campo nome deve ter entre 2 e 100 caracteres!")
	private String nome;

	public Mecanico fromDtoToEntity() {
		return new Mecanico().create(this.nome.trim());
	}

}
