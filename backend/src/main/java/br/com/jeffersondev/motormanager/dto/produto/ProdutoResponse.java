package br.com.jeffersondev.motormanager.dto.produto;

import java.math.BigDecimal;

import br.com.jeffersondev.motormanager.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoResponse {
	
	private String uuid;
	private String nome;
	private String med;
	private BigDecimal valor;
	private Boolean ativo;
	
	public Produto fromResponseToEntity() {
		return new Produto(this.uuid, this.nome, this.med, this.ativo, this.valor);
	}

}
