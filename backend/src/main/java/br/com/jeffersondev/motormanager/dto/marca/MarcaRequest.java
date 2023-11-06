package br.com.jeffersondev.motormanager.dto.marca;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.jeffersondev.motormanager.entity.Marca;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MarcaRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "O campo nome não pode ser nulo ou vazio!")
	@Size(min = 2, max = 100, message = "O campo nome deve ter entre 2 e 100 caracteres!")
	private String nome;

	public Marca fromDtoToEntity() {
		return new Marca().create(nome.trim());
	}
}
