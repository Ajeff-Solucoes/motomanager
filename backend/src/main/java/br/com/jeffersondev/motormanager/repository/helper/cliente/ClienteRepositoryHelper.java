package br.com.jeffersondev.motormanager.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.jeffersondev.motormanager.dto.filtro.ClienteFiltro;
import br.com.jeffersondev.motormanager.entity.cliente.Cliente;

public interface ClienteRepositoryHelper {
	
	Page<Cliente> filter(ClienteFiltro filtro, PageRequest pgRequest);

}
