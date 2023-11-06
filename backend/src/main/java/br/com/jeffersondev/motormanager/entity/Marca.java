package br.com.jeffersondev.motormanager.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.dto.marca.MarcaResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_marcas")
public class Marca{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	@Column(name = "ativo")
	private Boolean ativo;
	
	
	public Marca(final String uuid, final String nome, final Boolean ativo) {
		this.uuid = uuid;
		this.nome = nome;
		this.ativo = ativo;
	}	
	
	
	public Marca create(final String nome) {
		this.uuid = UUID.randomUUID().toString();
		this.ativo = true;
		this.nome = nome;
		return this;
	}
	


	public MarcaResponse fromEntityToResponse() {
		return new MarcaResponse(this.uuid, this.nome, this.ativo);
	}




}
