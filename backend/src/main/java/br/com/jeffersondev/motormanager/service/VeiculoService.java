package br.com.jeffersondev.motormanager.service;

import br.com.jeffersondev.motormanager.dto.cliente.VeiculoResponse;

public interface VeiculoService {

	void deleteById(String uuid);
	
	VeiculoResponse getById(String uuid);
}
