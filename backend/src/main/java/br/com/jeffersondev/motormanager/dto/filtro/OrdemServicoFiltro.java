package br.com.jeffersondev.motormanager.dto.filtro;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jeffersondev.motormanager.entity.os.StatusOS;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoFiltro {
	
	private Long codigo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataInicial;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataFinal;
	private StatusOS status;
	private String nome;
	private String placa;

}
