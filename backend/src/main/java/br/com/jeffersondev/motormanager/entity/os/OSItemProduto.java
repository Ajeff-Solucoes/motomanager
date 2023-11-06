package br.com.jeffersondev.motormanager.entity.os;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_ordem_servico_item_produto")
public class OSItemProduto{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	
	@Column(name = "quantidade")
	private Double quantidade;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	
	public OSItemProduto create(Double quantidade, BigDecimal valor, Produto produto) {
		this.uuid = UUID.randomUUID().toString();
		this.quantidade = quantidade;
		this.valor = valor;
		this.produto = produto;
		return this;
	}
	
	public BigDecimal subTotal() {
		return this.valor.multiply(BigDecimal.valueOf(this.quantidade));
	}

}
