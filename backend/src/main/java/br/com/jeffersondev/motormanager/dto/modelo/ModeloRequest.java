package br.com.jeffersondev.motormanager.dto.modelo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.jeffersondev.motormanager.entity.Marca;
import br.com.jeffersondev.motormanager.entity.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModeloRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@NotBlank(message = "O campo nome não pode ser nulo ou vazio!")
	@Size(min = 2, max = 100, message = "O campo nome deve ter entre 2 e 100 caracteres!")
	private String nome;
	
	@NotNull(message = "Informe o código da marca")
	private String marca;

	
	public Modelo fromDtoToEntity(Marca marca) {
		return new Modelo().create(this.nome.trim(), marca);
	}

	
}
