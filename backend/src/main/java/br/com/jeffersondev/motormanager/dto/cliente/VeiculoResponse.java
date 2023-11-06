package br.com.jeffersondev.motormanager.dto.cliente;

import br.com.jeffersondev.motormanager.entity.Modelo;
import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoResponse{
	
	private String uuid;
	private String placa;
	private String cor;
	private Integer ano;
	private Boolean ativo;
	private Modelo modelo;
	
	
	public Veiculo fromResponseToEntity() {
		return new Veiculo(this.uuid, this.placa, this.cor, this.ano, this.ativo, this.modelo);
	}
	
}
