package br.com.jeffersondev.motormanager.dto.marca;

import br.com.jeffersondev.motormanager.entity.Marca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MarcaResponse {
	
	private String uuid;
	private String nome;
	private Boolean ativo;
	
	public Marca fromResponseToEntity() {
		return new Marca (this.uuid, this.nome, this.ativo);
	}

}
