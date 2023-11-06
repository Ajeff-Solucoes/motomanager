package br.com.jeffersondev.motormanager.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.dto.mecanico.MecanicoResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_mecanicos")
public class Mecanico{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	@Column(name = "ativo")
	private Boolean ativo;
	
	
	public Mecanico(final String uuid, final String nome, final Boolean ativo) {
		this.uuid = uuid;
		this.nome = nome;
		this.ativo = ativo;
	}	
	
	public Mecanico create(final String nome) {
		this.uuid = UUID.randomUUID().toString();
		this.ativo = true;
		this.nome = nome;
		return this;
	}
	


	public MecanicoResponse fromEntityToResponse() {
		return new MecanicoResponse(this.uuid, this.nome, this.ativo);
	}
	

}
