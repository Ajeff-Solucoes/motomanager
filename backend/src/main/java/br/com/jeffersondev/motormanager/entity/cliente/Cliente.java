package br.com.jeffersondev.motormanager.entity.cliente;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.dto.cliente.ClienteResponse;
import br.com.jeffersondev.motormanager.dto.cliente.ClienteResponseSimples;
import br.com.jeffersondev.motormanager.dto.cliente.VeiculoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_clientes")
public class Cliente{

	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "ativo")
	private Boolean ativo;
	
	@Column(name = "email", length = 200)
	private String email;
	
	@Column(name = "celular", length = 20)
	private String celular;
	
	@Column(name = "telefone", length = 20)
	private String telefone;
	
	@Column(name = "nascimento")
	private LocalDate dataNascimento;

	@Column(name = "logradouro", length = 200)
	private String logradouro;
	
	@Column(name = "bairro", length = 100)
	private String bairro;
	
	@Column(name = "cidade", length = 50)
	private String cidade;
	
	@Column(name = "cep", length = 20)
	private String cep;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="cliente_id")
	private Set<Veiculo> veiculos = new HashSet<>();
	

	
	
	public Cliente create(final String nome, final String email, final String celular, final String telefone, final LocalDate dataNascimento,
			final String logradouro, final String bairro, final String cidade, final String cep, final Set<Veiculo> veiculos) {
		this.uuid = UUID.randomUUID().toString();
		this.ativo = true;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.veiculos = veiculos;
		return this;
	}


	public ClienteResponse fromEntityToResponse() {
		Set<VeiculoResponse> veiculos = this.veiculos.stream().map(Veiculo::fromEntityToResponse).collect(Collectors.toSet());
		ClienteResponse response = new ClienteResponse
				(this.uuid, this.nome, this.telefone, this.email, this.celular, this.dataNascimento, 
						this.getLogradouro(), this.getBairro(), this.getCidade(), 
						this.getCep(), this.ativo, veiculos);
		return response;
	}

	
	public ClienteResponseSimples fromEntityToResponseSimples() {
		return new ClienteResponseSimples(this.uuid, this.nome);
	}
}
