package br.com.jeffersondev.motormanager.dto.cliente;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.jeffersondev.motormanager.entity.Modelo;
import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoAddRequest{
	
	@NotBlank(message = "Informe o número da placa do veiculo")
	@Size(min = 7, max = 15, message = "O campo placa deve ter entre 7 e 10 caracteres!")
	private String placa;
	
	private String cor;
	
	private Integer ano;
	
	@NotBlank(message = "Informe o código do modelo do veiculo")
	private String modelo;

	@NotBlank(message = "Informe o código do cliente do veiculo")
	private String cliente;
	
	
	public Veiculo fromDtoToEntity(Modelo modelo) {
		Veiculo entity = new Veiculo().create(this.placa.trim(), this.cor.trim(), this.ano, modelo);
		return entity;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VeiculoAddRequest other = (VeiculoAddRequest) obj;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		return true;
	}

}
