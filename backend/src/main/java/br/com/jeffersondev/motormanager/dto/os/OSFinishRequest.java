package br.com.jeffersondev.motormanager.dto.os;

import java.math.BigDecimal;

import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OSFinishRequest{
	
	@Min(value = 0, message = "O valor do acréscimo não pode ser negativo")
	private BigDecimal acrescimos;

	@Min(value = 0, message = "O valor do desconto não pode ser negativo")
	private BigDecimal descontos;
}
