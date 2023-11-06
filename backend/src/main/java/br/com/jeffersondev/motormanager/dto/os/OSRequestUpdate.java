package br.com.jeffersondev.motormanager.dto.os;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OSRequestUpdate{
	
	@Size(max = 255, message = "O campo pendencias deve ter no máximo 255 caracteres!")
	private String pendencias;

	@Size(max = 255, message = "O campo observação deve ter no máximo 255 caracteres!")
	private String observacao;

}
