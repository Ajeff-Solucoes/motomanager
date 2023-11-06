package br.com.jeffersondev.motormanager.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_configuracoes")
public class Configuracao{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	@Column(name = "chave", length = 100, nullable = false)
	private String chave;
	@Column(name = "valor", length = 100, nullable = false)
	private String valor;
	
	@PrePersist
	public void PrePersist() {
		this.uuid = UUID.randomUUID().toString();
	}

	public Configuracao(final String chave, final String valor) {
		this.chave = chave;
		this.valor = valor;
	}
	
	
}
