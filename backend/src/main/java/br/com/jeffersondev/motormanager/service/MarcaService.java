package br.com.jeffersondev.motormanager.service;

import org.springframework.data.domain.Page;

import br.com.jeffersondev.motormanager.dto.marca.MarcaRequest;
import br.com.jeffersondev.motormanager.dto.marca.MarcaResponse;
import br.com.jeffersondev.motormanager.util.Paginacao;

public interface MarcaService {

	MarcaResponse getById(String uuid);

	void deleteById(String uuid);

	MarcaResponse create(MarcaRequest dto);

	Page<MarcaResponse> findAllAtivoAndOrderByNome(Paginacao paginacao);

	MarcaResponse update(String uuid, MarcaRequest dto);

	Page<MarcaResponse> filter(String nome, Paginacao paginacao);

}
