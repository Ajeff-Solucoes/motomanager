package br.com.jeffersondev.motormanager.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.cliente.ClienteResponse;
import br.com.jeffersondev.motormanager.dto.cliente.VeiculoResponse;
import br.com.jeffersondev.motormanager.dto.filtro.OrdemServicoFiltro;
import br.com.jeffersondev.motormanager.dto.home.FaturamentoTipoChartDTO;
import br.com.jeffersondev.motormanager.dto.mecanico.MecanicoResponse;
import br.com.jeffersondev.motormanager.dto.os.OSFinishRequest;
import br.com.jeffersondev.motormanager.dto.os.OSItemProdutoRequest;
import br.com.jeffersondev.motormanager.dto.os.OSItemServicoRequest;
import br.com.jeffersondev.motormanager.dto.os.OSRequestCreate;
import br.com.jeffersondev.motormanager.dto.os.OSRequestUpdate;
import br.com.jeffersondev.motormanager.dto.os.OSResponse;
import br.com.jeffersondev.motormanager.dto.os.OSUpdateStatusRequest;
import br.com.jeffersondev.motormanager.entity.os.OrdemServico;
import br.com.jeffersondev.motormanager.entity.os.StatusOS;
import br.com.jeffersondev.motormanager.exception.OperationNotExecutedException;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.exception.ValorInformadoInvalidoException;
import br.com.jeffersondev.motormanager.repository.OrdemServicoRepository;
import br.com.jeffersondev.motormanager.service.ClienteService;
import br.com.jeffersondev.motormanager.service.ItemProdutoService;
import br.com.jeffersondev.motormanager.service.ItemServicoService;
import br.com.jeffersondev.motormanager.service.MecanicoService;
import br.com.jeffersondev.motormanager.service.OrdemServicoService;
import br.com.jeffersondev.motormanager.service.VeiculoService;
import br.com.jeffersondev.motormanager.util.Paginacao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class OrdemServicoServiceImpl implements OrdemServicoService{
	
	
	private static final String MSG_NOTFOUND_ID = "Ordem de Serviço não encontrada";
	
	
	private final OrdemServicoRepository repository;
	private final VeiculoService veiculoService;
	private final ClienteService clienteService;
	private final MecanicoService mecanicoService;
	private final ItemServicoService itemServicoService;
	private final ItemProdutoService itemProdutoService;
	

	@Override
	@Transactional
	public OSResponse create(OSRequestCreate dto) {
		ClienteResponse cliente = clienteService.getById(dto.getCliente());
		VeiculoResponse veiculo = veiculoService.getById(dto.getVeiculo());
		MecanicoResponse mecanico = mecanicoService.getById(dto.getMecanico());
		
		OrdemServico entity = dto.fromDtoToEntity(cliente.fromResponseToEntity(), veiculo.fromResponseToEntity(), 
				mecanico.fromResponseToEntity());
		
		entity.setCodigo(setarCodigoOS());
		entity.setTotal(0.0);
		entity = repository.save(entity);
		return entity.fromEntityToResponse();
	}
	
	
	@Override
	public OSResponse getById(String uuid) {
		OrdemServico entity = this.findById(uuid);
		log.info("OrdemServico localizado com o UUID {}", uuid);
		return entity.fromEntityToResponse();
	}
	
	
	@Override
	public void deleteById(String uuid) {
		OrdemServico entity = this.findById(uuid);
		try {
			repository.delete(entity);
			log.info("Ordem Servico com UUID {} deletada", uuid);
		} catch (DataIntegrityViolationException e) {
			log.error("Erro de integridade ao tentar deletar o registro com o UUID: {} ", uuid );
			throw new OperationNotExecutedException("A O.S nº "+entity.getCodigo()+" tem relacionamento com outros registros e por isso não pode ser deletada!");
		}
	}
	
	
	@Override
	public Page<OSResponse> filter(OrdemServicoFiltro filtro, Paginacao paginacao) {
		validarDadosFiltroQuandoNulos(filtro);
		PageRequest pgRequest = PageRequest.of(paginacao.getPgNo()-1, paginacao.getSize());
		Page<OrdemServico> result = repository.filter(filtro, paginacao.getOrder(), pgRequest);
		return convertToResponsePage(result);
	}
		

	@Override
	public OSResponse update(String uuid, OSRequestUpdate dto) {
		OrdemServico entity = this.findById(uuid);
		entity.setObservacao(dto.getObservacao());
		entity.setPendencias(dto.getPendencias());
		entity = repository.save(entity);
		log.info("Ordem Servico com UUID {} alterada ", uuid);
		return entity.fromEntityToResponse();
	}
	
	
	@Override
	public String updateStatus(String uuid, OSUpdateStatusRequest status) {
		if(status == null ) throw new ValorInformadoInvalidoException("O valor do status é obrigatório");
		OrdemServico os = this.findById(uuid);
		log.info("Alterando o status da OS {} de {} para {}", os.getCodigo(), os.getStatus().toString(), status.getStatus());
		os.setStatus(StatusOS.getValue(status.getStatus()));
		repository.save(os);
		return os.getStatus().toString();
	}


	@Override
	public BigDecimal addItemServico(String uuid, OSItemServicoRequest servico) {
		OrdemServico os = this.findById(uuid);
		itemServicoService.addItem(os, servico);
		return os.getServicos().stream().map(s -> s.subTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	
	
	@Override
	public void addItemProduto(String uuid, OSItemProdutoRequest produto) {
		OrdemServico os = this.findById(uuid);
		itemProdutoService.addItem(os, produto);
		
	}

	
	@Override
	public BigDecimal removeItemServico(String os, String uuid) {
		OrdemServico ordem = this.findById(os);
		itemServicoService.removeItem(ordem, uuid);
		return ordem.getServicos().stream().map(s -> s.subTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	

	@Override
	public void removeItemProduto(String os, String uuid) {
		OrdemServico ordem = this.findById(os);
		itemProdutoService.removeItem(ordem, uuid);
	}
	
	
	@Override
	public void finalizarOS(String uuid, OSFinishRequest dto) {
		OrdemServico ordem = this.findById(uuid);
		ordem.setAcrescimos(dto.getAcrescimos());
		ordem.setDescontos(dto.getDescontos());
		ordem.setStatus(StatusOS.FECHADO);
		BigDecimal total = ordem.calcularTotal().add(ordem.getAcrescimos().subtract(ordem.getDescontos()));
		ordem.setTotal(total.doubleValue());		
		repository.save(ordem);
	}
	
	
	@Override
	public Map<String, Double> getQtdAll() {
		Map<String, Double> result = new HashMap<>();
		List<OrdemServico> list = repository.findAll();
		result.put("Quantidade", (double) list.size());
		result.put("Faturamento", list.stream()
				.map(OrdemServico::getTotal).reduce(0.0, (a,b) -> a+b));
		Optional<BigDecimal> tProd = Optional.ofNullable(list.stream().map(o -> o.calculaProdutos()).reduce(BigDecimal.ZERO, BigDecimal::add));
		Optional<BigDecimal> tServ = Optional.ofNullable(list.stream().map(o -> o.calculaServicos()).reduce(BigDecimal.ZERO, BigDecimal::add));
		result.put("Produtos", tProd.get().doubleValue());
		result.put("Servicos", tServ.get().doubleValue());
		
		return result;
	}
	
	
	
	@Override
	public List<FaturamentoTipoChartDTO> getBarCharts() {
		List<OrdemServico> list = repository.findAll();
		List<FaturamentoTipoChartDTO> result = new ArrayList<>();
		
		Map<LocalDate, Double> reser = list.stream()
				.collect(
						Collectors.groupingBy(
								o -> o.getData().with(TemporalAdjusters.firstDayOfMonth()), Collectors.summingDouble(OrdemServico::getTotal)));
		
		LocalDate hoje = LocalDate.now();
		
		List<LocalDate> semestre = new ArrayList<>();
		
		for(int i = 0; i < 6; i++) {
			LocalDate kk = hoje.minusMonths(i).withDayOfMonth(1);
			semestre.add(kk);
		}
		
		TreeMap<LocalDate, Double> edd = new TreeMap<>();
		edd.putAll(reser);
		
		
		for(LocalDate key : edd.keySet()) {
			Integer mes = key.getMonthValue();
			Integer ano = key.getYear();

			for(LocalDate d : semestre) {
				if(d.getMonthValue() == mes && d.getYear() == ano) {
					result.add(new FaturamentoTipoChartDTO(ano +"/"+mes, reser.get(key)));
				}
			}
		}
		return result;
	}


	@Override
	public Integer getAllByStatus(StatusOS status) {
		return repository.findAllByStatus(status).size();
	}
	
	
	private Long setarCodigoOS() {
		Optional<OrdemServico> codigo = repository.findAll().stream()
				.sorted(Comparator.comparingLong(OrdemServico::getCodigo)).reduce((first, second) -> second);
		return codigo.isEmpty() ? Long.valueOf(1) : codigo.get().getCodigo() + 1;
	}
	
	
	private OrdemServico findById(String uuid) {
		log.info("Tentando localizar a OrdemServico por UUID {}", uuid);
		return repository.findById(uuid)
				.orElseThrow(() -> new ResourceNotFoundException(MSG_NOTFOUND_ID));
	}
	
	
	private Page<OSResponse> convertToResponsePage(Page<OrdemServico> pagedResult) {
		return pagedResult.map(new Function<OrdemServico, OSResponse>() {
			@Override
			public OSResponse apply(OrdemServico entity) {
				return entity.fromEntityToResponse();
			}
		});
	}

	
	private void validarDadosFiltroQuandoNulos(OrdemServicoFiltro filtro) {
		if(filtro.getNome() == null || filtro.getNome().isBlank()) filtro.setNome("");
		if(filtro.getPlaca() == null || filtro.getPlaca().isBlank()) filtro.setPlaca("");
		if(filtro.getDataInicial() == null) filtro.setDataInicial(LocalDate.of(1500, 1, 1));
		if(filtro.getDataFinal() == null) filtro.setDataFinal(LocalDate.of(3000, 12, 31));
	}

}
