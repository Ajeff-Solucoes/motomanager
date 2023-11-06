package br.com.jeffersondev.motormanager.service;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.jeffersondev.motormanager.dto.servico.ServicoRequest;
import br.com.jeffersondev.motormanager.dto.servico.ServicoResponse;
import br.com.jeffersondev.motormanager.util.Paginacao;

public interface ServicoService {

	ServicoResponse getById(String uuid);

	void deleteById(String uuid);

	ServicoResponse create(ServicoRequest dto);

	Page<ServicoResponse> findAllAtivoAndOrderByNome(Paginacao paginacao);

	ServicoResponse update(String uuid, ServicoRequest dto);

	Page<ServicoResponse> filter(String nome, Paginacao paginacao);

	List<ServicoResponse> findByNome(String nome);

}
