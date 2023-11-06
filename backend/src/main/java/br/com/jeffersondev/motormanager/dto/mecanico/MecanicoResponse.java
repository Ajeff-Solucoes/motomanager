package br.com.jeffersondev.motormanager.dto.mecanico;

import br.com.jeffersondev.motormanager.entity.Mecanico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MecanicoResponse {
	
	private String uuid;
	private String nome;
	private Boolean ativo;
	
	public Mecanico fromResponseToEntity() {
		return new Mecanico(this.uuid, this.nome, this.ativo);
	}

}
