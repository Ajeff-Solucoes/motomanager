package br.com.jeffersondev.motormanager.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.os.OSItemServicoRequest;
import br.com.jeffersondev.motormanager.dto.servico.ServicoResponse;
import br.com.jeffersondev.motormanager.entity.os.OSItemServico;
import br.com.jeffersondev.motormanager.entity.os.OrdemServico;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.repository.ItemServicoRepository;
import br.com.jeffersondev.motormanager.service.ItemServicoService;
import br.com.jeffersondev.motormanager.service.ServicoService;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ItemServicoServiceImpl implements ItemServicoService{
	
	
	private final ItemServicoRepository repository;
	private final ServicoService servicoService;
	
	
	@Override
	public void addItem(OrdemServico os, OSItemServicoRequest dto) {
		ServicoResponse servico = servicoService.getById(dto.getServico());		
		OSItemServico item = new OSItemServico().create(dto.getQuantidade(), dto.getValor(), servico.fromResponseToEntity());
		os.getServicos().add(item);
	}


	@Override
	public void removeItem(OrdemServico ordem, String uuid) {
		List<OSItemServico> itens = ordem.getServicos().stream()
				.filter(s -> s.getUuid().equals(uuid)).collect(Collectors.toList());
		
		if(itens.isEmpty()) {
			throw new ResourceNotFoundException("Não foi encontrado o item de serviço na O.S para ser deletado");
		}
		ordem.getServicos().remove(itens.get(0));
		repository.deleteById(uuid);
	}
	
}
