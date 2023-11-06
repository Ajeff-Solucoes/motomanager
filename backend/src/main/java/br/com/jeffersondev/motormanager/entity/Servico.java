package br.com.jeffersondev.motormanager.entity;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.dto.servico.ServicoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_servicos")
public class Servico{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	@Column(name = "ativo")
	private Boolean ativo;
	@Column(name = "valor")
	private BigDecimal valor;
	
	
	
	public Servico create(final String nome, final BigDecimal valor) {
		this.uuid = UUID.randomUUID().toString();
		this.ativo = true;
		this.nome = nome;
		this.valor = valor;
		return this;
	}


	public ServicoResponse fromEntityToResponse() {
		return new ServicoResponse(this.uuid, this.nome, this.valor, this.ativo);
	}


}
