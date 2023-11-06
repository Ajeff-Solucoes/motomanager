package br.com.jeffersondev.motormanager.repository.helper.os;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.jeffersondev.motormanager.dto.filtro.OrdemServicoFiltro;
import br.com.jeffersondev.motormanager.entity.os.OrdemServico;

public interface OrdemServicoRepositoryHelper {
	
	Page<OrdemServico> filter(OrdemServicoFiltro filtro, String string, PageRequest pgRequest);


}
