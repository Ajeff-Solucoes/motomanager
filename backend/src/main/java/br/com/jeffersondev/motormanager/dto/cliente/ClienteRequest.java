package br.com.jeffersondev.motormanager.dto.cliente;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jeffersondev.motormanager.entity.cliente.Cliente;
import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest{
	
	@NotBlank(message = "Informe um nome para o cliente")
	@Size(min = 2, max = 100, message = "O campo nome deve ter entre 2 e 100 caracteres!")
	private String nome;
	
	private String telefone;
	
	@Size(max = 100, message = "O campo e-mail deve ter no máximo 100 caracteres!")
	private String email;
		
	@Size(max = 200, message = "O campo logradouro deve ter no máximo 200 caracteres!")
	private String logradouro;

	@Size(max = 100, message = "O campo bairro deve ter no máximo 100 caracteres!")
	private String bairro;

	@Size(max = 50, message = "O campo cidade deve ter no máximo 50 caracteres!")
	private String cidade;

	@Size(max = 8, message = "O campo CEP deve ter no máximo 8 caracteres!")
	private String cep;
	
	private Boolean ativo;
	
	private String celular;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate data_nascimento;
	
	@Size(min = 1, message = "O cliente deve, obrigatoriamente, possuir um veiculo")
	private Set<VeiculoAddRequest> veiculos = new HashSet<>();

	
	public Cliente fromDtoToEntity(Set<Veiculo> veiculos) {
		return new Cliente().create(this.nome.trim(), this.email.trim(), this.celular, this.telefone, this.data_nascimento, 
				this.logradouro.trim(), this.bairro.trim(), this.cidade.trim(), this.cep.trim(), veiculos);
	}


}
