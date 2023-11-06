package br.com.jeffersondev.motormanager.service;

import org.springframework.data.domain.Page;

import br.com.jeffersondev.motormanager.dto.modelo.ModeloRequest;
import br.com.jeffersondev.motormanager.dto.modelo.ModeloResponse;
import br.com.jeffersondev.motormanager.util.Paginacao;

public interface ModeloService {

	ModeloResponse getById(String uuid);

	void deleteById(String uuid);

	ModeloResponse create(ModeloRequest dto);

	Page<ModeloResponse> findAllAtivoAndOrderByNome(Paginacao paginacao);

	ModeloResponse update(String uuid, ModeloRequest dto);

	Page<ModeloResponse> filter(String nome, Paginacao paginacao);

}
