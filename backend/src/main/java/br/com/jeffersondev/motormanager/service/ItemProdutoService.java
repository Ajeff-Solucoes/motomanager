package br.com.jeffersondev.motormanager.service;

import br.com.jeffersondev.motormanager.dto.os.OSItemProdutoRequest;
import br.com.jeffersondev.motormanager.entity.os.OrdemServico;

public interface ItemProdutoService {

	void addItem(OrdemServico os, OSItemProdutoRequest servico);

	void removeItem(OrdemServico ordem, String uuid);
	
}
