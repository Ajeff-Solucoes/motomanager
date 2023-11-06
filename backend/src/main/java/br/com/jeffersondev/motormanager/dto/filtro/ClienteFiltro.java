package br.com.jeffersondev.motormanager.dto.filtro;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltro {
	
	private String nome;
	private String placa;
	
	public String getNome() {
		if(this.nome == null) return "";
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPlaca() {
		if(this.placa == null) return "";
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	
}
