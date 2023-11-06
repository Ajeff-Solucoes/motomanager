package br.com.jeffersondev.motormanager.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.dto.produto.ProdutoResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_produtos")
public class Produto{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	@Column(name = "med", length = 3, nullable = false)
	private String med;
	@Column(name = "ativo")
	private Boolean ativo;
	@Column(name = "valor")
	private BigDecimal valor;
	
	
	public Produto(final String uuid, final String nome, final String med, final Boolean ativo, final BigDecimal valor) {
		this.uuid = uuid;
		this.nome = nome;
		this.med = med;
		this.ativo = ativo;
		this.valor = valor;
	}
	
	
	public Produto create(final String nome, final String med, final BigDecimal valor) {
		this.uuid = UUID.randomUUID().toString();
		this.ativo = true;
		this.nome = nome;
		this.med = med;
		this.valor = valor;
		return this;
	}
	

	public ProdutoResponse fromEntityToResponse() {
		return new ProdutoResponse(this.uuid, this.nome, this.med, this.valor, this.ativo);
	}
}
