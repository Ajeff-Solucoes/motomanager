package br.com.jeffersondev.motormanager.dto.home;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FaturamentoTipoChartDTO {
	
	private String nome;
	private Double valor;

}
