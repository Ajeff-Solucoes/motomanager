package br.com.jeffersondev.motormanager.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.jeffersondev.motormanager.dto.os.OSItemProdutoRequest;
import br.com.jeffersondev.motormanager.dto.produto.ProdutoResponse;
import br.com.jeffersondev.motormanager.entity.os.OSItemProduto;
import br.com.jeffersondev.motormanager.entity.os.OrdemServico;
import br.com.jeffersondev.motormanager.exception.ResourceNotFoundException;
import br.com.jeffersondev.motormanager.repository.ItemProdutoRepository;
import br.com.jeffersondev.motormanager.service.ItemProdutoService;
import br.com.jeffersondev.motormanager.service.ProdutoService;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ItemProdutoServiceImpl implements ItemProdutoService{
	
	
	private final ItemProdutoRepository repository;
	private final ProdutoService produtoService;
	
	
	@Override
	public void addItem(OrdemServico os, OSItemProdutoRequest dto) {
		ProdutoResponse produto = produtoService.getById(dto.getProduto());		
		OSItemProduto item = new OSItemProduto().create(dto.getQuantidade(), dto.getValor(), produto.fromResponseToEntity());
		os.getProdutos().add(item);
	}


	@Override
	public void removeItem(OrdemServico ordem, String uuid) {
		List<OSItemProduto> itens = ordem.getProdutos().stream()
				.filter(p -> p.getUuid().equals(uuid)).collect(Collectors.toList());
		
		if(itens.isEmpty()) {
			throw new ResourceNotFoundException("Não foi encontrado o item de produto na O.S para ser deletado");
		}
		ordem.getProdutos().remove(itens.get(0));
		repository.deleteById(uuid);
	}
	
}
