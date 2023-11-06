package br.com.jeffersondev.motormanager.dto.cliente;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.jeffersondev.motormanager.entity.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponse{
	
	private String uuid;
	private String nome;
	private String telefone;
	private String email;
	private String celular;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String cep;
	private Boolean ativo;
	private Set<VeiculoResponse> veiculos;
	
	public ClienteResponse(String uuid, String nome, String telefone, String email, String celular,
			LocalDate dataNascimento, String logradouro, String bairro, String cidade, String cep, Boolean ativo) {
		this.uuid = uuid;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.celular = celular;
		this.dataNascimento = dataNascimento;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.ativo = ativo;
	}
	

	public Cliente fromResponseToEntity() {
		return new Cliente(this.uuid, this.nome, this.ativo, this.email, this.celular, this.telefone, this.dataNascimento, 
				this.logradouro, this.bairro, this.cidade, this.cep, 
				this.veiculos.stream().map(VeiculoResponse::fromResponseToEntity).collect(Collectors.toSet()));
	}
	
	
}
