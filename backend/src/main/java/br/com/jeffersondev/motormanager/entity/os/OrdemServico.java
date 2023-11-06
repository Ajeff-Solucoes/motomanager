package br.com.jeffersondev.motormanager.entity.os;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.jeffersondev.motormanager.dto.os.OSResponse;
import br.com.jeffersondev.motormanager.entity.Mecanico;
import br.com.jeffersondev.motormanager.entity.cliente.Cliente;
import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@Table(name = "tb_ordem_servico")
public class OrdemServico{
	
	@Id
	@Column(name = "id", length = 100, nullable = false)
	private String uuid;
	
	@Column(name = "codigo")
	private Long codigo;

	@Column(name = "ativo")
	private Boolean ativo;
	
	@Column(name = "data")
	private LocalDate data;
	
	@Column(name = "defeito", length = 255)
	private String defeito;

	@Column(name = "pendencias", length = 255)
	private String pendencias;

	@Column(name = "observacao", length = 255)
	private String observacao;

	@Column(name = "acrescimos")
	private BigDecimal acrescimos;

	@Column(name = "descontos")
	private BigDecimal descontos;
	
	@Column(name = "total")
	private Double total;
	
	@Enumerated(EnumType.STRING)
	private StatusOS status;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "id_veiculo")
	private Veiculo veiculo;
	
	@ManyToOne
	@JoinColumn(name = "id_mecanico")
	private Mecanico mecanico;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ordem_servico_id")
	private List<OSItemServico> servicos =new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "ordem_servico_id")
	private List<OSItemProduto> produtos = new ArrayList<>();
	
	
	
	public OrdemServico create(LocalDate data, String defeito, String observacao, Cliente cliente, Veiculo veiculo,
			Mecanico mecanico) {
		this.uuid = UUID.randomUUID().toString();
		this.status = StatusOS.ABERTO;
		this.ativo = true;
		this.descontos = BigDecimal.ZERO;
		this.acrescimos = BigDecimal.ZERO;
		this.servicos =  new ArrayList<>();
		this.produtos =  new ArrayList<>();
		this.data = data;
		this.defeito = defeito;
		this.observacao = observacao;
		this.cliente = cliente;
		this.veiculo = veiculo;
		this.mecanico = mecanico;
		return this;
	}
	
	
	
	public OSResponse fromEntityToResponse() {
		return new OSResponse(this.uuid, this.codigo, this.data, this.defeito, this.pendencias, this.observacao, this.cliente,
				this.veiculo, this.mecanico, this.status, this.acrescimos, this.descontos, this.servicos, this.produtos);
	}
	
	
	
//	public OrdemServico(final LocalDate data, final String defeito, final String observacao, final Cliente cliente, final Veiculo veiculo,
//			final Mecanico mecanico) {
//		this.dadosDefaultCreate();
//		this.data = data;
//		this.defeito = defeito;
//		this.observacao = observacao;
//		this.cliente = cliente;
//		this.veiculo = veiculo;
//		this.mecanico = mecanico;
//	}
//	public static OrdemServico createEntityByDTO(OSRequestCreate dto) {
//		return new OrdemServico(dto.getData(), dto.getDefeito(), dto.getObservacao(), dto.getCliente(), dto.getVeiculo(), 
//				dto.getMecanico());
//	}
	
	

	
	
//	public static OrdemServicoSimpleResponse entityToResponseSimple(OrdemServico entity) {
//		return new OrdemServicoSimpleResponse(entity.getUuid(), entity.getCodigo(), entity.getData(), entity.getDefeito(), 
//				entity.getObservacao(), entity.getCliente(), entity.getVeiculo(), entity.getStatus(), entity.calcularTotal());
//	}
	
	
	public BigDecimal calcularTotal() {
		return calculaProdutos().add(calculaServicos()).setScale(2);
	}

	
	public BigDecimal calculaServicos() {
		return  this.servicos.stream()
				.map(i -> i.subTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.setScale(2);
	}
	
	
	public BigDecimal calculaProdutos() {
		return  this.produtos.stream()
				.map(i -> i.subTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add)
				.setScale(2);
	}
//
//
//	private void dadosDefaultCreate() {
//		if(this.data == null) this.data = LocalDate.now();
//		this.uuid = UUID.randomUUID().toString();
//		this.status = StatusOS.ABERTO;
//		this.pendencias = "";
//		this.total = BigDecimal.ZERO;
//		this.totalServico = BigDecimal.ZERO;
//		this.totalProduto = BigDecimal.ZERO;
//		this.totalDesconto = BigDecimal.ZERO;
//		this.totalAcrescimo = BigDecimal.ZERO;
//	}

	

}
