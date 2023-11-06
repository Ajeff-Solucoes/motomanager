package br.com.jeffersondev.motormanager.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import br.com.jeffersondev.motormanager.dto.filtro.OrdemServicoFiltro;
import br.com.jeffersondev.motormanager.dto.home.FaturamentoTipoChartDTO;
import br.com.jeffersondev.motormanager.dto.os.OSFinishRequest;
import br.com.jeffersondev.motormanager.dto.os.OSItemProdutoRequest;
import br.com.jeffersondev.motormanager.dto.os.OSItemServicoRequest;
import br.com.jeffersondev.motormanager.dto.os.OSRequestCreate;
import br.com.jeffersondev.motormanager.dto.os.OSRequestUpdate;
import br.com.jeffersondev.motormanager.dto.os.OSResponse;
import br.com.jeffersondev.motormanager.dto.os.OSUpdateStatusRequest;
import br.com.jeffersondev.motormanager.entity.os.StatusOS;
import br.com.jeffersondev.motormanager.util.Paginacao;


public interface OrdemServicoService {

	OSResponse create(OSRequestCreate dto);
	
	OSResponse getById(String uuid);

	void deleteById(String uuid);

	OSResponse update(String uuid, OSRequestUpdate dto);

	Page<OSResponse> filter(OrdemServicoFiltro filter, Paginacao paginacao);

	String updateStatus(String uuid, OSUpdateStatusRequest status);

	BigDecimal addItemServico(String uuid, OSItemServicoRequest servico);

	void addItemProduto(String uuid, OSItemProdutoRequest produto);

	BigDecimal removeItemServico(String os, String uuid);

	void removeItemProduto(String os, String uuid);

	void finalizarOS(String uuid, OSFinishRequest dto);

	Map<String, Double> getQtdAll();

	Integer getAllByStatus(StatusOS fechado);

	List<FaturamentoTipoChartDTO> getBarCharts();

}
