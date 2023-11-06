package br.com.jeffersondev.motormanager.dto.servico;

import java.math.BigDecimal;

import br.com.jeffersondev.motormanager.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicoResponse {
	
	private String uuid;
	private String nome;
	private BigDecimal valor;
	private Boolean ativo;
	
	public Servico fromResponseToEntity() {
		return new Servico(this.uuid, this.nome, this.ativo, this.valor);
	}

}
