package br.com.jeffersondev.motormanager.dto.modelo;

import br.com.jeffersondev.motormanager.entity.Marca;
import br.com.jeffersondev.motormanager.entity.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloResponse {
	
	private String uuid;
	private String nome;
	private Boolean ativo;
	private Marca marca;
	
	public Modelo fromResponseToEntity() {
		return new Modelo(this.uuid, this.nome, this.ativo, this.marca);
	}
}
