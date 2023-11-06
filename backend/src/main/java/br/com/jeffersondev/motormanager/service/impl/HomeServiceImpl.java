package br.com.jeffersondev.motormanager.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.home.FaturamentoTipoChartDTO;
import br.com.jeffersondev.motormanager.dto.home.HomeResponse;
import br.com.jeffersondev.motormanager.entity.os.StatusOS;
import br.com.jeffersondev.motormanager.service.ClienteService;
import br.com.jeffersondev.motormanager.service.HomeService;
import br.com.jeffersondev.motormanager.service.OrdemServicoService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HomeServiceImpl implements HomeService{
	
	private final OrdemServicoService service;
	private final ClienteService clienteService;
	
	
	@Override	
	public HomeResponse getInfo() {
		Map<String, Double> getAll = service.getQtdAll();
		
		Integer total_os = getAll.get("Quantidade").intValue();
		Integer os_abertas = service.getAllByStatus(StatusOS.ABERTO);
		Integer total_clientes = clienteService.getQtdAll();
		Double total_faturamento = getAll.get("Faturamento");
		Double total_servicos = getAll.get("Servicos");
		Double total_produtos = getAll.get("Produtos");
		return new HomeResponse(total_os, total_faturamento, os_abertas, total_clientes, total_servicos, total_produtos);
	}


	@Override
	public List<FaturamentoTipoChartDTO> getBarChars() {
		return service.getBarCharts();	
	}
	
	
}
