package br.com.jeffersondev.motormanager.dto.os;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OSUpdateStatusRequest{
	
	@NotBlank(message = "Informe o valor do status")
	private String status;
}
