package br.com.jeffersondev.motormanager.dto.os;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jeffersondev.motormanager.entity.Mecanico;
import br.com.jeffersondev.motormanager.entity.cliente.Cliente;
import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;
import br.com.jeffersondev.motormanager.entity.os.OrdemServico;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OSRequestCreate {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@NotBlank(message = "Informe um defeito")
	@Size(max = 255, message = "O campo observação deve ter no máximo 255 caracteres!")
	private String defeito;
	
	@Size(max = 255, message = "O campo observação deve ter no máximo 255 caracteres!")
	private String observacao;
	
	@NotNull(message = "Informe o código do mecânico")
	private String mecanico;

	@NotNull(message = "Informe o código do cliente")
	private String cliente;

	@NotNull(message = "Informe o código do veiculo")
	private String veiculo;

	
	public OrdemServico fromDtoToEntity(final Cliente cliente, final Veiculo veiculo, final Mecanico mecanico) {
		return new OrdemServico().create(this.data, this.defeito, this.observacao, cliente, veiculo, mecanico);
	}

}
