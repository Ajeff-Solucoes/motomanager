package br.com.jeffersondev.motormanager.service;

import br.com.jeffersondev.motormanager.dto.os.OSItemServicoRequest;
import br.com.jeffersondev.motormanager.entity.os.OrdemServico;

public interface ItemServicoService {

	void addItem(OrdemServico os, OSItemServicoRequest servico);

	void removeItem(OrdemServico ordem, String uuid);
	
}
