package br.com.jeffersondev.motormanager.dto.os;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OSItemProdutoRequest{
	
	@NotNull(message = "Informe a quantidade")
	private Double quantidade;

	@NotNull(message = "Informe o valor")
	private BigDecimal valor;
	
	@NotBlank(message = "Informe código do produto")	
	private String produto;

	
}
