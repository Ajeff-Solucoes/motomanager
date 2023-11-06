package br.com.jeffersondev.motormanager.service;

import org.springframework.data.domain.Page;

import br.com.jeffersondev.motormanager.dto.mecanico.MecanicoRequest;
import br.com.jeffersondev.motormanager.dto.mecanico.MecanicoResponse;
import br.com.jeffersondev.motormanager.util.Paginacao;

public interface MecanicoService {

	MecanicoResponse getById(String uuid);

	void deleteById(String uuid);

	MecanicoResponse create(MecanicoRequest dto);

	Page<MecanicoResponse> findAllAtivoAndOrderByNome(Paginacao paginacao);

	MecanicoResponse update(String uuid, MecanicoRequest dto);

	Page<MecanicoResponse> filter(String nome, Paginacao paginacao);

}
