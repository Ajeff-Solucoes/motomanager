package br.com.jeffersondev.motormanager.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.dto.modelo.ModeloResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_modelos")
public class Modelo{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	@Column(name = "ativo")
	private Boolean ativo;
	@ManyToOne
	@JoinColumn(name = "id_marca")
	private Marca marca;
	
	
	public Modelo (String nome, Marca marca) {
		this.nome = nome;
		this.marca = marca;
	}

	public Modelo(final String uuid, final String nome, final Boolean ativo, final Marca marca) {
		this.uuid = uuid;
		this.nome = nome;
		this.ativo = ativo;
		this.marca = marca;
	}
	
	
	public ModeloResponse fromEntityToResponse() {
		return new ModeloResponse(this.uuid, this.nome, this.ativo, this.marca);
	}
	
	
	public Modelo create(final String nome, final Marca marca) {
		this.uuid = UUID.randomUUID().toString();
		this.ativo = true;
		this.nome = nome;
		this.marca = marca;
		return this;
	}


	
}
