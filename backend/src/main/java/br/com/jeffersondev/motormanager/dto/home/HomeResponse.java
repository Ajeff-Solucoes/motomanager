package br.com.jeffersondev.motormanager.dto.home;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HomeResponse {
	
	private Integer total_os;
	private Double total_faturamento;
	private Integer os_abertas;
	private Integer total_clientes;
	private Double total_servicos;
	private Double total_produtos;

}
