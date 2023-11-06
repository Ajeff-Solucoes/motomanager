package br.com.jeffersondev.motormanager.entity.cliente;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.dto.cliente.VeiculoResponse;
import br.com.jeffersondev.motormanager.entity.Modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_veiculos")
public class Veiculo{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	
	@Column(name = "placa", length = 15)
	private String placa;
	
	@Column(name = "cor", length = 30)
	private String cor;
	
	@Column(name = "ano")
	private Integer ano;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "id_modelo")
	private Modelo modelo;
	
	
	public Veiculo(final String placa, final String cor, final Integer ano, final Modelo modelo) {
		this.placa = placa;
		this.cor = cor;
		this.ano = ano;
		this.modelo = modelo;
	}
	
	
	public Veiculo create(final String placa, final String cor, final Integer ano, final Modelo modelo) {
		this.uuid = UUID.randomUUID().toString();
		this.ativo = true;
		this.placa = placa;
		this.cor = cor;
		this.ano = ano;
		this.modelo = modelo;
		return this;
	}
	
	
	public VeiculoResponse fromEntityToResponse() {
		return new VeiculoResponse(this.getUuid(), this.getPlaca(), this.getCor(), this.getAno(), this.getAtivo(), this.getModelo());
	}
}
