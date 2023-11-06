package br.com.jeffersondev.motormanager.entity.os;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_ordem_servico_item_servico")
public class OSItemServico{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	
	@Column(name = "quantidade")
	private Double quantidade;
	
	@Column(name = "valor")
	private BigDecimal valor;
	
	@ManyToOne
	@JoinColumn(name = "id_servico")
	private Servico servico;
	
	
	public OSItemServico create(final Double quantidade, final BigDecimal valor, final Servico servico) {
		this.uuid = UUID.randomUUID().toString();
		this.quantidade = quantidade;
		this.valor = valor;
		this.servico = servico;
		return this;
	}
	
	public BigDecimal subTotal() {
		return this.valor.multiply(BigDecimal.valueOf(this.quantidade));
	}
}
