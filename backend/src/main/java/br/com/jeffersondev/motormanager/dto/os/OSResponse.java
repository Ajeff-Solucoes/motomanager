package br.com.jeffersondev.motormanager.dto.os;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.jeffersondev.motormanager.entity.Mecanico;
import br.com.jeffersondev.motormanager.entity.cliente.Cliente;
import br.com.jeffersondev.motormanager.entity.cliente.Veiculo;
import br.com.jeffersondev.motormanager.entity.os.OSItemProduto;
import br.com.jeffersondev.motormanager.entity.os.OSItemServico;
import br.com.jeffersondev.motormanager.entity.os.StatusOS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OSResponse{
	
	private String uuid;
	private Long codigo;
	private LocalDate data;
	private String defeito;
	private String pendencias;
	private String observacao;
	private Cliente cliente;
	private Veiculo veiculo;
	private Mecanico mecanico;
	private StatusOS status;
	private BigDecimal acrescimos;
	private BigDecimal descontos;
	private List<OSItemServico> servicos = new ArrayList<>();
	private List<OSItemProduto> produtos = new ArrayList<>();
}
